//package com.api.vms;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
//import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.api.vms.controllers.VmsController;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
////@SpringBootTest(classes = VmsController.class)
//@AutoConfigureMockMvc
//@AutoConfigureJsonTesters
//@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.api:vmsnew:+:stubs:8090")
//class VmsApplicationTests {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	VmsController vmsController;
//
////	@Rule
////	public StubRunnerRule stubRunnerRule = new StubRunnerRule()
////			.downloadStub("com.api", "vmsnew", "0.0.1-SNAPSHOT", "stubs").withPort(8090)
////			.stubsMode(StubRunnerProperties.StubsMode.LOCAL);
////
////	@Test
////	public void clientShouldRetrunPersonForGivenID_checkFirsttName() throws Exception {
////		BDDAssertions.then(this.vmsController.requirement("REQ_28382020170712788")).isEqualTo(
////				"{'code':0,'message':'Requirement Details','data':[{'req_prop_id':713,'req_prop_type':'Creator Comments','req_prop_key':'REQ_28382020170712788','req_prop_value':{'taskName':'approveComments','acceptFlag':'Yes','attachment':'null','commentMsg':'approved','reviewFlag':'No','attachments':'null','creatorName':'creator','rejectedFlag':'No','reviewerName':'null','requirementID':'REQ_28382020170712788','attachmentName':'null','reviewAcceptFlag':'Yes'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null},{'req_prop_id':710,'req_prop_type':'Vendor Comments','req_prop_key':'REQ_28382020170712788','req_prop_value':{'taskName':'provideComments','acceptFlag':'No','attachment':'','commentMsg':'ok','reviewFlag':'No','creatorName':'vendor','rejectedFlag':'No','requirementID':'REQ_28382020170712788','attachmentName':'','reviewAcceptFlag':'No'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null},{'req_prop_id':709,'req_prop_type':'Requirement Creation','req_prop_key':'REQ_28382020170712788','req_prop_value':{'dueDate':'Fri Aug 28 2020 00:00:00 GMT+0530 (India Standard Time)','assignTo':'vendor','priority':'5','taskName':'createRequirement','eventType':'Listing','initiator':'creator','createdDate':'Tue Jul 28 17:38:13 IST 2020','creatorName':'creator','requirementID':'REQ_28382020170712788','documentUpload':'','requirementType':'Marketing','requirementTitle':'reqnew','documentUploadName':'','requirementDetails':['Other'],'requirementDescription':'desc'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null}]}");
////	}
//
////	@Test
////	public void given_WhenPassEvenNumberInQueryParam_ThenReturnEven() throws Exception {
////
////		mockMvc.perform(MockMvcRequestBuilders.post("/getRequirementDetailsApi?inputJson=REQ_28382020170712788")
////				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
////				.andExpect(MockMvcResultMatchers.content().string(
////						"{'code':0,'message':'Requirement Details','data':[{'req_prop_id':713,'req_prop_type':'Creator Comments','req_prop_key':'REQ_28382020170712788','req_prop_value':{'taskName':'approveComments','acceptFlag':'Yes','attachment':'null','commentMsg':'approved','reviewFlag':'No','attachments':'null','creatorName':'creator','rejectedFlag':'No','reviewerName':'null','requirementID':'REQ_28382020170712788','attachmentName':'null','reviewAcceptFlag':'Yes'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null},{'req_prop_id':710,'req_prop_type':'Vendor Comments','req_prop_key':'REQ_28382020170712788','req_prop_value':{'taskName':'provideComments','acceptFlag':'No','attachment':'','commentMsg':'ok','reviewFlag':'No','creatorName':'vendor','rejectedFlag':'No','requirementID':'REQ_28382020170712788','attachmentName':'','reviewAcceptFlag':'No'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null},{'req_prop_id':709,'req_prop_type':'Requirement Creation','req_prop_key':'REQ_28382020170712788','req_prop_value':{'dueDate':'Fri Aug 28 2020 00:00:00 GMT+0530 (India Standard Time)','assignTo':'vendor','priority':'5','taskName':'createRequirement','eventType':'Listing','initiator':'creator','createdDate':'Tue Jul 28 17:38:13 IST 2020','creatorName':'creator','requirementID':'REQ_28382020170712788','documentUpload':'','requirementType':'Marketing','requirementTitle':'reqnew','documentUploadName':'','requirementDetails':['Other'],'requirementDescription':'desc'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null}]}"));
////	}
//
////	@Test
////	public void given_WhenPassEvenNumberInQueryParam_ThenReturnEven() throws Exception {
////
////		mockMvc.perform(MockMvcRequestBuilders.post("/testContract")).andExpect(MockMvcResultMatchers.status().isOk())
////				.andExpect(MockMvcResultMatchers.content().string("checked"));
////	}
//
//	@Test
//	public void given_WhenPassEvenNumberInQueryParam_ThenReturnEven() throws Exception {
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/getRequirementDetailsApi?inputJson=REQ_28382020170712788"))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.content().string("{'code':0,'message':'Requirement Details','data':[{'req_prop_id':713,'req_prop_type':'Creator Comments','req_prop_key':'REQ_28382020170712788','req_prop_value':{'taskName':'approveComments','acceptFlag':'Yes','attachment':'null','commentMsg':'approved','reviewFlag':'No','attachments':'null','creatorName':'creator','rejectedFlag':'No','reviewerName':'null','requirementID':'REQ_28382020170712788','attachmentName':'null','reviewAcceptFlag':'Yes'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null},{'req_prop_id':710,'req_prop_type':'Vendor Comments','req_prop_key':'REQ_28382020170712788','req_prop_value':{'taskName':'provideComments','acceptFlag':'No','attachment':'','commentMsg':'ok','reviewFlag':'No','creatorName':'vendor','rejectedFlag':'No','requirementID':'REQ_28382020170712788','attachmentName':'','reviewAcceptFlag':'No'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null},{'req_prop_id':709,'req_prop_type':'Requirement Creation','req_prop_key':'REQ_28382020170712788','req_prop_value':{'dueDate':'Fri Aug 28 2020 00:00:00 GMT+0530 (India Standard Time)','assignTo':'vendor','priority':'5','taskName':'createRequirement','eventType':'Listing','initiator':'creator','createdDate':'Tue Jul 28 17:38:13 IST 2020','creatorName':'creator','requirementID':'REQ_28382020170712788','documentUpload':'','requirementType':'Marketing','requirementTitle':'reqnew','documentUploadName':'','requirementDetails':['Other'],'requirementDescription':'desc'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null}]}"));
//	}
//
//}
