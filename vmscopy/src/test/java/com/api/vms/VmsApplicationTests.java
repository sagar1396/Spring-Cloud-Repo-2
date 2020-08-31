//package com.api.vms;
//
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
//
//import com.api.vms.controllers.VmsController;
//
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@DirtiesContext
//@AutoConfigureMessageVerifier
//class VmsApplicationTests {
//	
//	@Autowired
//	VmsController vmsController;
//
//	 @Before
//	    public void setup() {
//	        StandaloneMockMvcBuilder standaloneMockMvcBuilder 
//	          = MockMvcBuilders.standaloneSetup(vmsController);
//	        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
//	    }
//
//}
