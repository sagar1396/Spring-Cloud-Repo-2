package com.api.vms.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.vms.beans.RequirementDetails;
import com.api.vms.services.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RefreshScope
@RequestMapping("/vms")
public class VmsController {

	@Autowired
	ApiService apiService;

	@Value("${messageCheck}")
	private String messageCheck;

	ObjectMapper objectMapper = new ObjectMapper();

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String MAIN_SERVICE = "mainService";

	@RequestMapping("/checkConfigProperty")
	public String checkConfigProperty() {
		return messageCheck;
	}

	@PostMapping("/process/start/{processKey}")
	// @CrossOrigin
	public String startProcess(@RequestBody RequirementDetails requirementDetails, @PathVariable String processKey,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String obj = "";
		try {
			//RequirementDetails requirementDetails = objectMapper.readValue(inputJson, RequirementDetails.class);
			obj = apiService.startProcessService(requirementDetails, request);

		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return obj;

	}

	@PostMapping("/process/completeTask/{taskId}/{topicName}")
	// @CrossOrigin
	public String completeTask(@ModelAttribute RequirementDetails requirementDetails, @PathVariable String taskId,
			@PathVariable String topicName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject returnObj = new JSONObject();
		logger.info("requirementDetails ------- " + requirementDetails);
		try {
			String errorMsg = apiService.completeTaskService(taskId, topicName, requirementDetails);
			if (errorMsg.equals("")) {
				returnObj.put("code", 0);
				returnObj.put("message", "Success");
				returnObj.put("data", "Requirement created.");
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			logger.error("Exception", e);
			response.setStatus(500);
			returnObj.put("code", 1);
			returnObj.put("message", "Error");
			returnObj.put("data", e.getMessage());
		}
		return returnObj.toString();

	}

	@PostMapping("/process/fetchAndLock/{topicName}")
	// @CrossOrigin
	public String fetchAndLockTask(@RequestBody RequirementDetails requirementDetails, @PathVariable String topicName,
			HttpServletRequest request, HttpServletResponse response) {
		String returnString = "";
		System.out.println("requirementDetails - " + requirementDetails);
		try {
			//RequirementDetails requirementDetails = objectMapper.readValue(inputJson, RequirementDetails.class);
			returnString = apiService.fetchAndLockService(requirementDetails, topicName);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnString;

	}

	
	@GetMapping("/process/requirement/{reqId}")
	// @CrossOrigin
	//@HystrixCommand(fallbackMethod = "requirementFallback")
	@CircuitBreaker(fallbackMethod  = "requirementFallback", name = "requirementFallback")
	public String requirement(@PathVariable String reqId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String requirementDetails = "";
		System.out.println("reqId - " + reqId);
		try {
			requirementDetails = apiService.getRequirementDetailsApi(reqId);
			// requirementDetails = apiService.getRequirementDetails(reqId);
			//throw new Exception();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(500);
			return e.getMessage();
		}
		return requirementDetails;

	}

	//@GetMapping("/process/requirementFallback/{reqId}")
	public String requirementFallback(String reqId, HttpServletRequest request,
			HttpServletResponse response
			, Exception e
			) {
		String[] arr = { "" };
		List<Map<String, Object>> requirementDetails = new ArrayList<Map<String, Object>>();
		JSONObject returnObj = new JSONObject();
		returnObj.put("code", 0);
		returnObj.put("message", "Fallback method called");
		returnObj.put("data", requirementDetails);
		return returnObj.toString();

	}

	@GetMapping("/process/comments/{reqId}")
	// @CrossOrigin
	public String comments(@PathVariable String reqId, HttpServletRequest request, HttpServletResponse response) {
		String requirementDetails = "";
		System.out.println("reqId - " + reqId);
		try {
			requirementDetails = apiService.getRequirementComments(reqId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requirementDetails;

	}

	@GetMapping("/process/getNextTask/{process_instance_id}")
	// @CrossOrigin
	public String getNextTask(@PathVariable String process_instance_id, HttpServletRequest request,
			HttpServletResponse response) {
		String requirementDetails = "";
		System.out.println("process_instance_id - " + process_instance_id);
		try {
			requirementDetails = apiService.getNextTask(process_instance_id, request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requirementDetails;

	}

	@PostMapping("/process/changeRating")
	// @CrossOrigin
	public String changeRating(@RequestBody String inputJson, HttpServletRequest request,
			HttpServletResponse response) {
		String returnString = "";
		System.out.println("json - " + inputJson);
		try {
			Map<String, String> requirementDetails = objectMapper.readValue(inputJson, Map.class);
			returnString = apiService.changeRating(requirementDetails);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnString;

	}

}
