package com.api.vms.daos;

import java.util.List;
import java.util.Map;

import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.api.vms.beans.RequirementDetails;

@Repository
public class ApiDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertRequirement(RequirementDetails requirementDetails) {

		return jdbcTemplate.update(
				"INSERT INTO requirement_table(req_id,req_name,req_creator,req_vendor,req_status,created_by,updated_by,"
						+ "            req_instance_id,req_defination_id,req_parent_instance_id)"
						+ "            VALUES(?,?,?,?,?,?,?,?,?,?)",
				requirementDetails.getRequirementID(), requirementDetails.getRequirementDescription(),
				requirementDetails.getCreatorName(), "vendor", "ACTIVE", requirementDetails.getCreatorName(),
				requirementDetails.getCreatorName(), requirementDetails.getReqInstanceID(),
				requirementDetails.getReqDefinitionID(), requirementDetails.getReqParentID());

	}

	public int insertRequirementProperty(RequirementDetails requirementDetails, PGobject json, String type) {

		return jdbcTemplate.update(
				"INSERT INTO requirement_property(req_prop_type, req_prop_key, req_prop_value, created_by, updated_by)"
						+ "            VALUES(?,?,?,?,?)",
				type, requirementDetails.getRequirementID(), json, requirementDetails.getCreatorName(),
				requirementDetails.getCreatorName());

	}

	public int updateRequirementChild(RequirementDetails requirementDetails) {
		return jdbcTemplate.update(
				"UPDATE requirement_table SET req_instance_id = ?,updated_date=now() WHERE req_id = ? ;",
				requirementDetails.getReqInstanceID(), requirementDetails.getRequirementID());

	}

	public int updateRequirementCreator(RequirementDetails requirementDetails) {
		return jdbcTemplate.update("UPDATE requirement_table SET req_creator = ?,updated_date=now() WHERE req_id = ? ;",
				requirementDetails.getCreatorName(), requirementDetails.getRequirementID());

	}

	public int updateRequirementReviewer(RequirementDetails requirementDetails) {
		return jdbcTemplate.update(
				"UPDATE requirement_table SET req_reviewer = ?,updated_date=now() WHERE req_id = ? ;",
				requirementDetails.getReviewerName(), requirementDetails.getRequirementID());

	}

	public int completeRequirement(RequirementDetails requirementDetails) {
		return jdbcTemplate.update(
				"UPDATE requirement_table set  req_status='INACTIVE',updated_by = ?,closure_date=now(),updated_date=now() where req_id = ? ;",
				requirementDetails.getCreatorName(), requirementDetails.getRequirementID());

	}

	public int addNotification(String reqId) {
		String sql = "INSERT INTO user_req_prop_notification(un_user_id,un_req_prop_id) "
				+ "        SELECT user_id,req_prop_id from (SELECT p.req_prop_id,ul.user_id," + "                    ("
				+ "                    CASE WHEN ui.ui_role ='vendor' THEN"
				+ "                                        CASE"
				+ "                                            WHEN (p.req_prop_type = 'Requirement Creation' AND p.req_prop_value->>'initiator' = 'vendor')  THEN 'created an idea.'"
				+ "                                            WHEN (p.req_prop_type = 'Requirement Creation' AND p.req_prop_value->>'initiator' = 'creator')  THEN 'assigned to you.'"
				+ "                                            WHEN p.req_prop_type = 'Vendor Comments' THEN 'sent comment.'"
				+ "                                            WHEN (p.req_prop_type = 'Creator Comments' AND p.req_prop_value->>'reviewFlag' = 'Yes') THEN 'sent for review'"
				+ "                                            WHEN ((p.req_prop_type = 'Creator Comments' OR p.req_prop_type ='Reviewer Comments') AND p.req_prop_value->>'reviewAcceptFlag'='Yes') THEN 'approved.'"
				+ "                                            WHEN ((p.req_prop_type = 'Creator Comments' OR p.req_prop_type ='Reviewer Comments') AND p.req_prop_value->>'reviewAcceptFlag'='No' AND p.req_prop_value->>'rejectedFlag'='Yes') THEN 'rejected.'"
				+ "                                            WHEN ((p.req_prop_type = 'Reviewer Comments' OR p.req_prop_type = 'Creator Comments' )AND p.req_prop_value->>'reviewAcceptFlag'='No') THEN 'received comment.'"
				+ "                                            ELSE ''" + "                                        END"
				+ "                        WHEN ui.ui_role ='creator' THEN"
				+ "                                        CASE"
				+ "                                            WHEN (p.req_prop_type = 'Requirement Creation' AND p.req_prop_value->>'initiator' = 'creator') THEN 'created a requirement.'"
				+ "                                            WHEN (p.req_prop_type = 'Requirement Creation' AND p.req_prop_value->>'initiator' = 'vendor') THEN 'recieved an idea.'"
				+ "                                            WHEN p.req_prop_type = 'Vendor Comments' THEN 'received comment.'"
				+ "                                            WHEN (p.req_prop_type = 'Creator Comments' AND p.req_prop_value->>'reviewFlag' = 'Yes') THEN 'sent for review'"
				+ "                                            WHEN (p.req_prop_type = 'Creator Comments' AND p.req_prop_value->>'reviewAcceptFlag' = 'Yes') THEN 'approved.'"
				+ "                                            WHEN (p.req_prop_type = 'Creator Comments' AND p.req_prop_value->>'reviewAcceptFlag' = 'No' AND p.req_prop_value->>'rejectedFlag'='Yes' ) THEN 'rejected.'"
				+ "                                            WHEN (p.req_prop_type = 'Creator Comments' AND p.req_prop_value->>'reviewAcceptFlag'='No' AND p.req_prop_value->>'rejectedFlag'='No') THEN 'sent comment.'"
				+ "                                            WHEN (p.req_prop_type = 'Reviewer Comments' AND p.req_prop_value->>'reviewAcceptFlag'='Yes') THEN 'approved.'"
				+ "                                            WHEN (p.req_prop_type = 'Reviewer Comments' AND p.req_prop_value->>'reviewAcceptFlag'='No' AND p.req_prop_value->>'rejectedFlag'='Yes') THEN 'rejected.'"
				+ "                                            WHEN (p.req_prop_type = 'Reviewer Comments' AND p.req_prop_value->>'reviewAcceptFlag'='No') THEN 'received comment.'"
				+ "                                            ELSE ''" + "                                        END"
				+ "                        WHEN ui.ui_role ='reviewer' THEN"
				+ "                                        CASE"
				+ "                                            WHEN p.req_prop_type = 'Creator Comments' AND p.req_prop_value->>'reviewFlag' = 'Yes' THEN 'assigned to you.'"
				+ "                                            WHEN (p.req_prop_type = 'Reviewer Comments' AND p.req_prop_value->>'reviewAcceptFlag'='Yes') THEN 'approved.'"
				+ "                                            WHEN (p.req_prop_type = 'Creator Comments' AND p.req_prop_value->>'reviewAcceptFlag' = 'No' AND p.req_prop_value->>'rejectedFlag'='Yes') THEN 'rejected.'"
				+ "                                            WHEN (p.req_prop_type = 'Reviewer Comments' AND p.req_prop_value->>'reviewAcceptFlag' = 'No' AND p.req_prop_value->>'rejectedFlag'='No') THEN 'sent comment.'"
				+ "                                            ELSE ''		"
				+ "                                        END" + "                        ELSE ''"
				+ "                    END" + "                    ) msg"
				+ "                    ,ui.ui_role,r.req_creator , r.req_vendor,r.req_reviewer"
				+ "                    FROM requirement_table r INNER JOIN requirement_property p ON p.req_prop_key = r.req_id, "
				+ "                    user_info ui,user_list ul" + "                    WHERE "
				+ "                    ui.ui_user_name = ul.user_name"
				+ "                    --AND (r.req_creator = ui.ui_user_name OR r.req_vendor = ui.ui_user_name OR r.req_reviewer = ui.ui_user_name) "
				+ "                    AND ((r.req_creator = ui.ui_user_name OR r.req_vendor = ui.ui_user_name OR r.req_reviewer = ui.ui_user_name) "
				+ "                    OR " + "                    (r.created_by = r.req_vendor))"
				+ "                    AND user_status = upper('ACTIVE')" + "                    AND p.req_prop_id = ?"
				+ "                    --AND r.created_by = ui.ui_user_name "
				+ "                     ORDER BY p.created_date DESC) as reqList WHERE msg <> '';";

		return jdbcTemplate.update(sql, reqId);

	}

	public List<Map<String, Object>> getRequirements(String reqId) {
		return jdbcTemplate.queryForList(
				"SELECT *,(SELECT ui_email_id from user_info where ui_user_name =rt.req_creator  ) as creator_email "
						+ "        , (SELECT rpub.publish_url as publish_remark from requirement_publication rpub where rpub.req_id = rt.req_id LIMIT 1 )"
						+ "        ,(SELECT rpub.created_date as publish_date from requirement_publication rpub where rpub.req_id = rt.req_id LIMIT 1)"
						+ "        ,(SELECT rpub.created_by as publish_by from requirement_publication rpub where rpub.req_id = rt.req_id LIMIT 1)"
						+ "        from requirement_property rp INNER JOIN requirement_table rt ON rp.req_prop_key = rt.req_id "
						+ "        WHERE rt.req_id = ? " + "        GROUP BY rp.req_prop_id,rt.req_id "
						+ "        ORDER BY rp.updated_date DESC",
				reqId);

	}

	public List<Map<String, Object>> getCommentsRequirement(String reqId) {
		return jdbcTemplate.queryForList(
				" select * from requirement_property rp " + "        where rp.req_prop_key = ? " + "        AND "
						+ "        (" + "            rp.req_prop_type = 'Vendor Comments' "
						+ "            OR rp.req_prop_type = 'Creator Comments' "
						+ "            OR rp.req_prop_type = 'Reviewer Comments'" + "        )"
						+ "        GROUP BY rp.req_prop_key,rp.req_prop_id  " + "        ORDER BY rp.updated_date DESC",
				reqId);
	}

	public int changeRating(String reqId, String updatedBy) {

		return jdbcTemplate.update(
				"INSERT INTO requirement_rating(req_rating,req_id,updated_by,created_by,created_date,updated_date)\r\n"
						+ "                values (?,?,?,?,now(),now())",
				5, reqId, updatedBy,updatedBy);

	}
}
