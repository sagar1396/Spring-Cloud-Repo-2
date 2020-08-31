//package com.api.vmsnew;
//
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.api.vmsnew.controllers.VmsnewController;
//
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
////@SpringBootTest(classes = VmsnewApplication.class)
//@DirtiesContext
//@AutoConfigureMessageVerifier
//public class VmsnewApplicationTests {
//
//	@InjectMocks
//	VmsnewController vmsController;
//	
//	private MockMvc mockMvc;
//
////	@MockBean
////	ApiService apiService;
//
//	@Autowired
//	WebApplicationContext webApplicationContext;
//
////	@Before
////	public void before() throws Exception {
////		final String returnString = "{'code':0,'message':'Requirement Details','data':[{'req_prop_id':713,'req_prop_type':'Creator Comments','req_prop_key':'REQ_28382020170712788','req_prop_value':{'taskName':'approveComments','acceptFlag':'Yes','attachment':'null','commentMsg':'approved','reviewFlag':'No','attachments':'null','creatorName':'creator','rejectedFlag':'No','reviewerName':'null','requirementID':'REQ_28382020170712788','attachmentName':'null','reviewAcceptFlag':'Yes'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null},{'req_prop_id':710,'req_prop_type':'Vendor Comments','req_prop_key':'REQ_28382020170712788','req_prop_value':{'taskName':'provideComments','acceptFlag':'No','attachment':'','commentMsg':'ok','reviewFlag':'No','creatorName':'vendor','rejectedFlag':'No','requirementID':'REQ_28382020170712788','attachmentName':'','reviewAcceptFlag':'No'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null},{'req_prop_id':709,'req_prop_type':'Requirement Creation','req_prop_key':'REQ_28382020170712788','req_prop_value':{'dueDate':'Fri Aug 28 2020 00:00:00 GMT+0530 (India Standard Time)','assignTo':'vendor','priority':'5','taskName':'createRequirement','eventType':'Listing','initiator':'creator','createdDate':'Tue Jul 28 17:38:13 IST 2020','creatorName':'creator','requirementID':'REQ_28382020170712788','documentUpload':'','requirementType':'Marketing','requirementTitle':'reqnew','documentUploadName':'','requirementDetails':['Other'],'requirementDescription':'desc'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null}]}";
////		Mockito.when(this.vmsController.getRequirementDetailsApi("REQ_28382020170712788")).thenReturn(returnString);
////		RestAssuredMockMvc.standaloneSetup(this.vmsController);
////	}
//
//	@Before
//	public void setup() {
//		// StandaloneMockMvcBuilder standaloneMockMvcBuilder =
//		// MockMvcBuilders.standaloneSetup(vmsController);
//		// RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
//		MockitoAnnotations.initMocks(this);
//		RestAssuredMockMvc.mockMvc(MockMvcBuilders.standaloneSetup(vmsController).build());
//	}
//
////	@Before
////	public void setup() {
////		String returnString = "";
////		StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup();
////		RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
////	}
//
////	@Before public void setup() {
////	    RestAssuredMockMvc.standaloneSetup(vmsController);
////
////	    Mockito.when(vmsController.testContract())
////	        .thenReturn("checked");
////	  }
//
////	@Before
////	public void setup() throws Exception {
////		RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
////		RestAssuredMockMvc.standaloneSetup(vmsController);
////
////		Mockito.when(apiService.getRequirementDetails("REQ_28382020170712788")).thenReturn(
////				"{'code':0,'message':'Requirement Details','data':[{'req_prop_id':713,'req_prop_type':'Creator Comments','req_prop_key':'REQ_28382020170712788','req_prop_value':{'taskName':'approveComments','acceptFlag':'Yes','attachment':'null','commentMsg':'approved','reviewFlag':'No','attachments':'null','creatorName':'creator','rejectedFlag':'No','reviewerName':'null','requirementID':'REQ_28382020170712788','attachmentName':'null','reviewAcceptFlag':'Yes'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null},{'req_prop_id':710,'req_prop_type':'Vendor Comments','req_prop_key':'REQ_28382020170712788','req_prop_value':{'taskName':'provideComments','acceptFlag':'No','attachment':'','commentMsg':'ok','reviewFlag':'No','creatorName':'vendor','rejectedFlag':'No','requirementID':'REQ_28382020170712788','attachmentName':'','reviewAcceptFlag':'No'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null},{'req_prop_id':709,'req_prop_type':'Requirement Creation','req_prop_key':'REQ_28382020170712788','req_prop_value':{'dueDate':'Fri Aug 28 2020 00:00:00 GMT+0530 (India Standard Time)','assignTo':'vendor','priority':'5','taskName':'createRequirement','eventType':'Listing','initiator':'creator','createdDate':'Tue Jul 28 17:38:13 IST 2020','creatorName':'creator','requirementID':'REQ_28382020170712788','documentUpload':'','requirementType':'Marketing','requirementTitle':'reqnew','documentUploadName':'','requirementDetails':['Other'],'requirementDescription':'desc'},'created_by':'creator','created_date':'2020-07-28T12:08:13.080Z','updated_by':'creator','updated_date':'2020-07-28T12:12:16.963Z','req_id':'REQ_28382020170712788','req_name':'desc','req_creator':'creator','req_vendor':'vendor','req_status':'INACTIVE','req_instance_id':'02fdfea1-d0cb-11ea-b464-6c4b9099838d','req_defination_id':'vendor_management:1:7fb0256a-cc0e-11ea-bf6e-6c4b9099838d','req_reviewer':null,'req_serial':356,'closure_date':'2020-07-28T12:12:16.963Z','req_parent_instance_id':'02a1fbcd-d0cb-11ea-b464-6c4b9099838d','creator_email':'sringe@nseit.com','publish_remark':null,'publish_date':null,'publish_by':null}]}");
////	
//
////	}
//
//}
