package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import api.endpoints.userEndpoints;
import api.payload.User;
import api.utilities.ExtentReportManager;
import api.endpoints.userKeys;
import io.restassured.response.Response;
import api.validation.*;

public class userTest 
{
    Faker faker;
    User userPayload;
    public int fke_idNumber;
    public Logger logger;
    userTestValidation uservalidate = new userTestValidation();
    
	@BeforeClass
	public void setupUserData()
	{
		faker = new Faker();
		userPayload = new User();

		fke_idNumber =userPayload.setId(faker.idNumber().hashCode());
		String fke_userName = userPayload.setUsername(faker.name().username());
		String fke_fName = userPayload.setFirstName(faker.name().firstName());
		String fke_lName = userPayload.setLastName(faker.name().lastName());
		String fke_email = userPayload.setEmail(faker.internet().safeEmailAddress());
		String fke_pass = userPayload.setEmail(faker.internet().password());
		String fke_num = userPayload.setPhone(faker.phoneNumber().cellPhone());
        System.out.println("Id Number is: " + fke_idNumber);
        
        //logs
        logger = LogManager.getLogger(this.getClass());
	}
	
	/*-- update payload */
	public void setupUpdate() 
	{
		//Change the field for update
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
	}
	/*-- end of update payload */
	
	@Test(priority =1, enabled = true)
	public void testPostUser()
	{
		logger.info("***************** Creating User *****************");
		Response response = userEndpoints.createUser(userPayload);
		uservalidate.postuserValidation(response);//call the valdiation of psotuservalidation
		logger.info("HTTP Response Body:\n" + response.getBody().asString());//logs the response
		logger.info("***************** User is Created *****************");//logs
		ExtentReportManager.setResponse(response);//get the response and will use in the extentreport
		    
	}

	@Test(priority = 2, enabled = true)
	public void testGetUser() 
	{
		logger.info("***************** Read user Info *****************");
		
		Response response = userEndpoints.getUser(userPayload.getUsername());
		
		uservalidate.getuserValidation(response, userPayload,fke_idNumber);
		
		logger.info("HTTP Response Body:\n" + response.getBody().asString());
		logger.info("***************** User Info is displayed *****************");
		ExtentReportManager.setResponse(response);
	}
	
	@Test(priority = 3, enabled = true)
	public void test_UpdateUser() 
	{
		logger.info("***************** Updating User *****************");		
		setupUpdate();//call the method update payload		
		Response response = userEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
		uservalidate.updateuserValidation(response);
		logger.info("HTTP Response Body:\n" + response.getBody().asString());
		testGetUser();//validate the response update
		logger.info("***************** User is updated *****************");
		ExtentReportManager.setResponse(response);
	}
	
/* Start of Test DeleteUser */	
	@Test(priority = 4, enabled = true)
	public void testDeleteUser()
	{
		logger.info("***************** Deleting User *****************");
		Response response = userEndpoints.delUser(userPayload.getUsername());
		logger.info("HTTP Response Body:\n" + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***************** User is Deleted *****************");
		ExtentReportManager.setResponse(response);
	}
/* End of Test DeleteUser */	
	
}
