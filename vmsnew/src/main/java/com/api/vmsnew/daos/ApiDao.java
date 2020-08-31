package com.api.vmsnew.daos;

import java.util.List;
import java.util.Map;

import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.api.vmsnew.beans.RequirementDetails;

@Repository
public class ApiDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

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

}
