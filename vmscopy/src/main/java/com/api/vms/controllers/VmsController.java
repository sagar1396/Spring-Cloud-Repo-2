package com.api.vms.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.vms.beans.RequirementDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class VmsController {

	ObjectMapper objectMapper = new ObjectMapper();

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@PostMapping("/prepareJsonObject")
	// @CrossOrigin
	public String prepareJsonObject(@RequestBody String inputJson, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String obj = "";
		try {
			RequirementDetails requirementDetails = objectMapper.readValue(inputJson, RequirementDetails.class);
			obj = prepareJsonObject(requirementDetails.getTaskName(), requirementDetails);
			logger.info("Response of prepareJsonObject - "+obj);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;

	}

	public String prepareJsonObject(String taskName, RequirementDetails requirementDetails) {
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
		return obj.toString();
	}

}
