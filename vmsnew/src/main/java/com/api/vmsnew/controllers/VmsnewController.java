package com.api.vmsnew.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.vmsnew.beans.RequirementDetails;
import com.api.vmsnew.services.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@EnableAutoConfiguration
public class VmsnewController {

	@Autowired
	ApiService apiService;

	ObjectMapper objectMapper = new ObjectMapper();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/testContract")
	public String testContract() {
		return "checked";
	}

	@PostMapping("/prepareJsonBForDao")
	// @CrossOrigin
	public String prepareJsonBForDao(@RequestBody String inputJson, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String obj = "";
		try {
			RequirementDetails requirementDetails = objectMapper.readValue(inputJson, RequirementDetails.class);
			obj = prepareJsonObjectForDao(requirementDetails.getTaskName(), requirementDetails);
			logger.info("Response of prepareJsonBForDao -- " + obj);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;

	}

	@PostMapping("/getRequirementDetailsApi")
	public String getRequirementDetailsApi(@RequestParam("inputJson") String inputJson) throws Exception {
		String obj = "";
		try {
			obj = apiService.getRequirementDetails(inputJson);
			logger.info("Response of getRequirementDetailsApi -- " + obj);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;

	}

	public String prepareJsonObjectForDao(String taskName, RequirementDetails requirementDetails) {
		JSONObject jobj = new JSONObject();
		if (taskName.equals("createRequirement")) {
			jobj.put("dueDate", requirementDetails.getDueDate());
			jobj.put("assignTo", requirementDetails.getAssignTo());
			jobj.put("priority", requirementDetails.getPriority());
			jobj.put("taskName", taskName);
			jobj.put("eventType", requirementDetails.getEventType());
			jobj.put("initiator", requirementDetails.getInitiator());
			jobj.put("createdDate", new Date());
			jobj.put("creatorName", requirementDetails.getCreatorName());
			jobj.put("requirementID", requirementDetails.getRequirementID());
			jobj.put("documentUpload", requirementDetails.getDocumentUpload());
			jobj.put("requirementType", requirementDetails.getRequirementType());
			jobj.put("requirementTitle", requirementDetails.getRequirementTitle());
			jobj.put("documentUploadName", requirementDetails.getDocumentUploadName());
			jobj.put("requirementDetails", requirementDetails.getRequirementDetails());
			jobj.put("documentEncryptedName", requirementDetails.getDocumentEncryptedName());
			jobj.put("requirementDescription", requirementDetails.getRequirementDescription());
			requirementDetails.setUsername(requirementDetails.getCreatorName());

		}
		if (taskName.equals("provideComments")) {
			jobj.put("taskName", taskName);
			jobj.put("acceptFlag", requirementDetails.getAcceptFlag());
			jobj.put("attachment", requirementDetails.getAttachment());
			jobj.put("commentMsg", requirementDetails.getCommentMsg());
			jobj.put("reviewFlag", requirementDetails.getReviewFlag());
			jobj.put("attachments", requirementDetails.getAttachments());
			jobj.put("creatorName", requirementDetails.getCreatorName());
			jobj.put("requirementID", requirementDetails.getRequirementID());
			jobj.put("rejectedFlag", requirementDetails.getRejectedFlag());
			jobj.put("attachmentName", requirementDetails.getAttachmentName());
			jobj.put("reviewAcceptFlag", requirementDetails.getReviewAcceptFlag());
			jobj.put("encryptedAttachmentName", requirementDetails.getEncryptedAttachmentName());
			requirementDetails.setUsername(requirementDetails.getCreatorName());
		}
		if (taskName.equals("approveComments")) {
			jobj.put("taskName", taskName);
			jobj.put("acceptFlag", requirementDetails.getAcceptFlag());
			jobj.put("attachment", requirementDetails.getAttachment());
			jobj.put("commentMsg", requirementDetails.getCommentMsg());
			jobj.put("reviewFlag", requirementDetails.getReviewFlag());
			jobj.put("attachments", requirementDetails.getAttachment());
			jobj.put("creatorName", requirementDetails.getCreatorName());
			jobj.put("rejectedFlag", requirementDetails.getRejectedFlag());
			jobj.put("reviewerName", requirementDetails.getReviewerName());
			jobj.put("requirementID", requirementDetails.getRequirementID());
			jobj.put("attachmentName", requirementDetails.getAttachmentName());
			jobj.put("reviewAcceptFlag", requirementDetails.getReviewAcceptFlag());
			jobj.put("encryptedAttachmentName", requirementDetails.getEncryptedAttachmentName());
			requirementDetails.setUsername(requirementDetails.getCreatorName());
		}

		return jobj.toString();
	}

}
