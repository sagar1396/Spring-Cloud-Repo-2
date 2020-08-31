package com.api.vms.services;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.api.vms.beans.RequirementDetails;
import com.api.vms.daos.ApiDao;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ApiService {

	@Value("${camunda_host}")
	private String camunda_host;

	@Value("${camunda_port}")
	private String camunda_port;

	@Value("${camunda_process_key}")
	private String camunda_process_key;

	@Autowired
	private ApiDao apiDao;

	@Autowired
	private RestTemplate restTemplate;

	DateFormat dateFormat = new SimpleDateFormat("ddmmyyyyHHMMssSSS");

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public String getCamundaUrl() {
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		sb.append(camunda_host);
		sb.append(":");
		sb.append(camunda_port);
		sb.append("/engine-rest");

		return sb.toString();
	}

	public String setVariableBody(HttpServletRequest request, String taskName, RequirementDetails requirementDetails) {
		switch (taskName) {
		case "processInstance":
			JSONObject obj = new JSONObject();
			obj.put("businessKey", requirementDetails.getRequirementID());
			JSONObject obj1 = new JSONObject();
			obj1.put("value", requirementDetails.getRequirementID());
			obj1.put("type", "String");
			JSONObject obj2 = new JSONObject();
			obj2.put("value", requirementDetails.getCreatorName());
			obj2.put("type", "String");
			JSONObject variables = new JSONObject();
			variables.put("requirementID", obj1);
			variables.put("initiator", obj2);
			obj.put("variables", variables);

			return obj.toString();
		}
		return "";

	}

	public String startProcessService(RequirementDetails requirementDetails, HttpServletRequest request) {
		Date sysCrntDate = new Date();
		JSONObject returnObj = new JSONObject();
		requirementDetails.setRequirementID("REQ_" + dateFormat.format(sysCrntDate));
		logger.info("Requirement Id ------------------------------- " + requirementDetails.getRequirementID());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject obj = new JSONObject();
		obj.put("businessKey", requirementDetails.getRequirementID());
		JSONObject obj1 = new JSONObject();
		obj1.put("value", requirementDetails.getRequirementID());
		obj1.put("type", "String");
		JSONObject obj2 = new JSONObject();
		obj2.put("value", requirementDetails.getCreatorName());
		obj2.put("type", "String");
		JSONObject variables = new JSONObject();
		variables.put("requirementID", obj1);
		variables.put("initiator", obj2);
		obj.put("variables", variables);
		HttpEntity<String> httpEntity = new HttpEntity<>(obj.toString(), headers);
		URI uri;
		try {
			uri = new URI(getCamundaUrl() + "/process-definition/key/" + camunda_process_key + "/start");
			Map<String, String> map = new RestTemplate().postForObject(uri, httpEntity, Map.class);
			requirementDetails.setReqParentID(map.get("id"));
			requirementDetails.setReqDefinitionID(map.get("definitionId"));
			requirementDetails.setReqInstanceID(map.get("id"));

			apiDao.insertRequirement(requirementDetails);

			returnObj.put("code", 0);
			returnObj.put("message", "Success");
			JSONObject data = new JSONObject();
			data.put("requirementID", requirementDetails.getRequirementID());
			returnObj.put("data", data);
			request.setAttribute("requirementID", requirementDetails.getRequirementID());
		} catch (URISyntaxException e) {
			logger.error("Exception", e);
		}

		return returnObj.toString();

	}

	public String fetchAndLockService(RequirementDetails requirementDetails, String topicName) {
		String returnString = "";
		JSONObject returnObj = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject obj = new JSONObject();
		obj.put("workerId", "nseit_1");
		obj.put("usePriority", "true");
		obj.put("maxTasks", 300);
		JSONArray ja = new JSONArray();
		JSONObject obj2 = new JSONObject();
		obj2.put("topicName", topicName);
		obj2.put("lockDuration", 10000);
		JSONObject obj3 = new JSONObject();
		obj3.put("requirementID", requirementDetails.getRequirementID());
		obj2.put("processVariables", obj3);
		ja.put(obj2);
		obj.put("topics", ja);

		HttpEntity<String> httpEntity = new HttpEntity<>(obj.toString(), headers);
		URI uri;
		try {
			uri = new URI(getCamundaUrl() + "/external-task/fetchAndLock");
			Object[] str = new RestTemplate().postForObject(uri, httpEntity, Object[].class);
			Map<String, Object> map1 = (Map<String, Object>) str[0];
			returnObj.put("code", 0);
			returnObj.put("message", "Success");
			JSONObject data = new JSONObject();
			data.put("taskId", map1.get("id"));
			returnObj.put("data", data);
			// request.setAttribute("requirementID", requirementDetails.getRequirementID());
		} catch (URISyntaxException e) {
			logger.error("Exception", e);
		}
		return returnObj.toString();
	}

	public String callForJsonObjectForHeaders(String taskName, RequirementDetails requirementDetails) throws RestClientException {
		String errorMsg  = "";
		String returnJson = "";
		requirementDetails.setTaskName(taskName);
		try {
			returnJson = restTemplate.postForObject("http://vms-copy/prepareJsonObject", requirementDetails,
					String.class);
			logger.info("response from vms-copy - " + returnJson);
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return returnJson;
	}

	public String completeTaskService(String taskId, String taskName, RequirementDetails requirementDetails) throws Exception {
		String errorMsg = "";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String jsonObject = callForJsonObjectForHeaders(taskName, requirementDetails);
		HttpEntity<String> httpEntity = new HttpEntity<>(jsonObject, headers);
		URI uri;
		try {
			uri = new URI(getCamundaUrl() + "/external-task/" + taskId + "/complete");
			Map<String, String> map = new RestTemplate().postForObject(uri, httpEntity, Map.class);

			PGobject pgobj = new PGobject();
			pgobj.setType("jsonb");
			try {
				pgobj.setValue(prepareJsonObjectForDao(taskName, requirementDetails).toString());
			} catch (SQLException e) {
				logger.info("Exception",e);
				errorMsg = e.getMessage();
				
			}
			apiDao.insertRequirementProperty(requirementDetails, pgobj, getTypeOfRequirement(taskName));
			if (taskName.equalsIgnoreCase("createRequirement")) {
				HttpEntity<String> httpEntity1 = new HttpEntity<>(headers);
				URI uri1 = new URI(
						getCamundaUrl() + "/process-instance?processDefinitionKey=vms_requirement&businessKey="
								+ requirementDetails.getRequirementID());
				Object[] str = new RestTemplate().getForObject(uri1, Object[].class);
				Map<String, Object> map1 = (Map<String, Object>) str[0];
				requirementDetails.setReqInstanceID(String.valueOf(map1.get("id")));
				apiDao.updateRequirementChild(requirementDetails);
			}
			if (requirementDetails.getInitiator() != null)
				if (taskName.equals("approveComments") && requirementDetails.getInitiator().equalsIgnoreCase("vendor")
						&& !requirementDetails.getCreatorName().equalsIgnoreCase(requirementDetails.getUsername())) {
					requirementDetails.setCreatorName(requirementDetails.getUsername());
					apiDao.updateRequirementCreator(requirementDetails);

				}
			if (taskName.equalsIgnoreCase("approveComments")
					&& requirementDetails.getReviewFlag().equalsIgnoreCase("Yes")) {
				apiDao.updateRequirementReviewer(requirementDetails);

			}
			if ((taskName.equals("giveApproval") || taskName.equals("approveComments"))
					&& (requirementDetails.getReviewAcceptFlag().equalsIgnoreCase("Yes")
							|| requirementDetails.getRejectedFlag().equalsIgnoreCase("Yes"))) {
				//requirementDetails.setCreatorName(requirementDetails.getUsername());
				apiDao.completeRequirement(requirementDetails);

			}

			// apiDao.addNotification(requirementDetails.getRequirementID());

		} catch (URISyntaxException e) {
			logger.error("Exception", e);
			errorMsg = e.getMessage();
		} catch (Exception e) {
			logger.error("Exception", e);
			errorMsg = e.getMessage();
			
		}
		return errorMsg;
	}

	public String getTypeOfRequirement(String taskName) {
		String type = "";
		if (taskName.equalsIgnoreCase("createRequirement"))
			type = "Requirement Creation";
		if (taskName.equalsIgnoreCase("provideComments"))
			type = "Vendor Comments";
		if (taskName.equalsIgnoreCase("approveComments"))
			type = "Creator Comments";
		return type;
	}

	public String prepareJsonObjectForDao(String taskName, RequirementDetails requirementDetails) {
		String returnJson = "";
		requirementDetails.setTaskName(taskName);
		try {
			returnJson = restTemplate.postForObject("http://vmsnew/prepareJsonBForDao", requirementDetails,
					String.class);
			logger.info("response from vms-new - " + returnJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnJson;
	}

	public JSONObject prepareJsonObject(String taskName, RequirementDetails requirementDetails) {
		JSONObject obj = new JSONObject();
		if (taskName.equals("createRequirement")) {
			obj.put("workerId", "nseit_1");
			JSONObject variables = new JSONObject();

			JSONObject obj1 = new JSONObject();
			obj1.put("value", requirementDetails.getRequirementTitle());
			obj1.put("type", "String");
			variables.put("requirementTitle", obj1);

			JSONObject obj2 = new JSONObject();
			obj2.put("value", requirementDetails.getEventType());
			obj2.put("type", "String");
			variables.put("eventType", obj2);

			JSONObject obj3 = new JSONObject();
			obj3.put("value", requirementDetails.getRequirementDetails());
			obj3.put("type", "String");
			variables.put("requirementDetails", obj3);

			JSONObject obj4 = new JSONObject();
			obj4.put("value", requirementDetails.getRequirementOther());
			obj4.put("type", "String");
			variables.put("requirementOther", obj4);

			JSONObject obj5 = new JSONObject();
			obj5.put("value", requirementDetails.getRequirementDescription());
			obj5.put("type", "String");
			variables.put("requirementDescription", obj5);

			JSONObject obj6 = new JSONObject();
			obj6.put("value", requirementDetails.getDueDate());
			obj6.put("type", "String");
			variables.put("dueDate", obj6);

			JSONObject obj7 = new JSONObject();
			obj7.put("value", requirementDetails.getAssignTo());
			obj7.put("type", "String");
			variables.put("assignTo", obj7);

			JSONObject obj8 = new JSONObject();
			obj8.put("value", requirementDetails.getPriority());
			obj8.put("type", "String");
			variables.put("priority", obj8);

			JSONObject obj9 = new JSONObject();
			obj9.put("value", requirementDetails.getDocumentUpload());
			obj9.put("type", "String");
			variables.put("documentUpload", obj9);

			JSONObject obj10 = new JSONObject();
			obj10.put("value", requirementDetails.getCreatorName());
			obj10.put("type", "String");
			variables.put("creatorName", obj10);

			JSONObject obj11 = new JSONObject();
			obj11.put("value", requirementDetails.getCreatedDate());
			obj11.put("type", "String");
			variables.put("createdDate", obj11);

			JSONObject obj12 = new JSONObject();
			obj12.put("value", requirementDetails.getRequirementID());
			obj12.put("type", "String");
			variables.put("requirementID", obj12);

			obj.put("variables", variables);
		}
		if (taskName.equals("provideComments")) {
			obj.put("workerId", "nseit_1");
			JSONObject variables = new JSONObject();

			JSONObject obj1 = new JSONObject();
			obj1.put("value", requirementDetails.getCommentMsg());
			obj1.put("type", "String");
			variables.put("vendorCommentMsg", obj1);

			JSONObject obj6 = new JSONObject();
			obj6.put("value", requirementDetails.getAttachmentName());
			obj6.put("type", "String");
			variables.put("attachments", obj6);

			obj.put("variables", variables);
		}
		if (taskName.equals("approveComments")) {

			obj.put("workerId", "nseit_1");
			JSONObject variables = new JSONObject();

			JSONObject obj1 = new JSONObject();
			obj1.put("value", requirementDetails.getAcceptFlag());
			obj1.put("type", "String");
			variables.put("acceptFlag", obj1);

			JSONObject obj2 = new JSONObject();
			obj2.put("value", requirementDetails.getRejectedFlag());
			obj2.put("type", "String");
			variables.put("rejectedFlag", obj2);

			JSONObject obj3 = new JSONObject();
			obj3.put("value", requirementDetails.getCreatorCommentMsg());
			obj3.put("type", "String");
			variables.put("creatorCommentMsg", obj3);

			JSONObject obj4 = new JSONObject();
			obj4.put("value", requirementDetails.getReviewFlag());
			obj4.put("type", "String");
			variables.put("reviewFlag", obj4);

			JSONObject obj5 = new JSONObject();
			obj5.put("value", requirementDetails.getReviewerName());
			obj5.put("type", "String");
			variables.put("reviewerName", obj5);

			JSONObject obj6 = new JSONObject();
			obj6.put("value", requirementDetails.getAttachmentName());
			obj6.put("type", "String");
			variables.put("attachments", obj6);

			obj.put("variables", variables);

		}
		return obj;
	}

	public String getRequirementDetails(String reqId) throws Exception {
		List<Map<String, Object>> requirementDetails = apiDao.getRequirements(reqId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
		for (Map<String, Object> m : requirementDetails) {

			Date created_date = (Date) m.get("created_date");
			Date updated_date = (Date) m.get("updated_date");
			if (m.get("closure_date") == null) {
			} else {
				Date closure_date = (Date) m.get("closure_date");
				m.put("closure_date", dateFormat.format(closure_date));
			}
			m.put("created_date", dateFormat.format(created_date));
			m.put("updated_date", dateFormat.format(updated_date));
			requirementDetails.set(requirementDetails.indexOf(m), m);
		}

		org.json.simple.JSONObject returnObj = new org.json.simple.JSONObject();
		returnObj.put("code", 0);
		returnObj.put("message", "Requirement Details");
		// org.json.simple.JSONObject dataobject = new
		// org.json.simple.JSONObject(requirementDetails);
		// org.json.simple.JSONArray data = new org.json.simple.JSONArray();
		// data.add(dataobject);
		returnObj.put("data", requirementDetails);

		return returnObj.toString();
	}
	
	//@CircuitBreaker(fallbackMethod = "requirementFallback", name = "default")
	public String getRequirementDetailsApi(String reqId) {
		String returnJson = "";
		try {
			returnJson = restTemplate.postForObject("http://vmsnew/getRequirementDetailsApi?inputJson="+reqId,null,
					String.class);
			logger.info("response from vms-new - " + returnJson);
			if(returnJson==null) {
				throw new Exception();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnJson;
	}
	
	public String requirementFallback(String reqId, Throwable t) {
		return "Fallback Method Called";
	}

	public String getRequirementComments(String reqId) {
		List<Map<String, Object>> requirementDetails = apiDao.getCommentsRequirement(reqId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
		for (Map<String, Object> m : requirementDetails) {

			Date created_date = (Date) m.get("created_date");
			Date updated_date = (Date) m.get("updated_date");
			m.put("created_date", dateFormat.format(created_date));
			m.put("updated_date", dateFormat.format(updated_date));
			requirementDetails.set(requirementDetails.indexOf(m), m);
		}
		org.json.simple.JSONObject returnObj = new org.json.simple.JSONObject();
		returnObj.put("code", 0);
		returnObj.put("message", "Requirement Comments");
		// org.json.simple.JSONObject dataobject = new
		// org.json.simple.JSONObject(requirementDetails);
		// org.json.simple.JSONArray data = new org.json.simple.JSONArray();
//		if(requirementDetails.size()>0) {
//			data.add(requirementDetails);
//		}

		returnObj.put("data", requirementDetails);

		return returnObj.toString();
	}

	public String getNextTask(String process_instance_id, HttpServletRequest request, HttpServletResponse response) {
		HttpHeaders headers = new HttpHeaders();
		JSONObject returnObj = new JSONObject();
		headers.setContentType(MediaType.APPLICATION_JSON);
		org.json.simple.JSONObject jsonObj = new org.json.simple.JSONObject();
		jsonObj.put("todo", "Get Tasks");
		jsonObj.put("processInstanceId", process_instance_id);
		HttpEntity<org.json.simple.JSONObject> httpEntity = new HttpEntity<>(jsonObj, headers);
		URI uri;
		try {
			uri = new URI(getCamundaUrl() + "/external-task/");
			String str = new RestTemplate().postForObject(uri, httpEntity, String.class);
			returnObj.put("code", 0);
			returnObj.put("message", "Success");
			returnObj.put("data", new JSONArray(str));
			// request.setAttribute("requirementID", requirementDetails.getRequirementID());
		} catch (URISyntaxException e) {
			logger.error("Exception", e);
		}
		return returnObj.toString();
	}

	public String changeRating(Map<String, String> requirementDetails) {
		JSONObject returnObj = new JSONObject();
		apiDao.changeRating(requirementDetails.get("req_id"), requirementDetails.get("updatedBy"));
		returnObj.put("code", 0);
		returnObj.put("message", "Success");
		returnObj.put("data", "Requirement rating changed");

		return returnObj.toString();

	}

}
