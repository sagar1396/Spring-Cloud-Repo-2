import org.springframework.cloud.contract.spec.Contract
Contract.make {
	description "return json string"
	request{
		method POST()
		url("/prepareJsonObject") {
			queryParameters {
				parameter("number", "2")
			}
		}
	}
	response {
		body("Even")
		status 200
	}
}