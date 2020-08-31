package com.api.vmsnew.services;

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

import com.api.vmsnew.beans.RequirementDetails;
import com.api.vmsnew.daos.ApiDao;

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
	
	
}
