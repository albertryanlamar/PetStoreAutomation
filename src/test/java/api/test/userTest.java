package api.test;

import static org.testng.Assert.assertEquals;
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

public class userTest 
{
    Faker faker;
    User userPayload;
    public int fke_idNumber;
    public Logger logger;
    
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
	
	@Test(priority =1)
	public void testPostUser()
	{
		logger.info("***************** Creating User *****************");
		
		Response response = userEndpoints.createUser(userPayload);
		String messag = response.jsonPath().getString("message");
		
		response.then()
			.assertThat()
			.body("type", is("unknown"))
			.body("message",is(messag))
			.log().all();
		    Assert.assertEquals(response.getStatusCode(), 200);
		    logger.info("HTTP Response Body:\n" + response.getBody().asString());
			// System.out.println("Id Number 3: " + userPayload.getId());
		    logger.info("***************** User is Created *****************");
		    ExtentReportManager.setResponse(response);
		    
	}

	@Test(priority = 2)
	public void testGetUser() 
	{
		logger.info("***************** Read user Info *****************");
		Response response = userEndpoints.getUser(userPayload.getUsername());
		
		response.then()
		    .assertThat()
				.body(userKeys.id,is(equalTo(userPayload.getId())))
				.body(userKeys.uname, is(userPayload.getUsername()))
				.body(userKeys.fname,is(userPayload.getFirstName()))
				.body(userKeys.lname, is(userPayload.getLastName()))
				.body(userKeys.em, is(userPayload.getEmail()))
				.body(userKeys.pass, is(userPayload.getPassword()))
				.body(userKeys.cpnum, is(userPayload.getPhone()))
			.log().all();
		logger.info("HTTP Response Body:\n" + response.getBody().asString());
			Assert.assertEquals(response.getStatusCode(), 200);
			Assert.assertEquals(fke_idNumber,userPayload.getId());
			//System.out.println("Username : " + userPayload.getUsername());
			logger.info("***************** User Info is displayed *****************");
			ExtentReportManager.setResponse(response);
	}
	
	@Test(priority = 3)
	public void test_UpdateUser() 
	{
		logger.info("***************** Updating User *****************");
		setupUpdate();//call the method update payload
		Response response = userEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
		String messag = response.jsonPath().getString("message");
		logger.info("HTTP Response Body:\n" + response.getBody().asString());
		response.then()
				.assertThat()
					.body("type", is("unknown"))
					.body("message",is(messag))
					.log().all();
					 Assert.assertEquals(response.getStatusCode(), 200);
					 testGetUser();//validate the response update
		logger.info("***************** User is updated *****************");
		ExtentReportManager.setResponse(response);
	}
	@Test(priority = 4)
	public void testDeleteUser() 
	{
		logger.info("***************** Deleting User *****************");
		Response response = userEndpoints.delUser(userPayload.getUsername());
		logger.info("HTTP Response Body:\n" + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***************** User is Deleted *****************");
		ExtentReportManager.setResponse(response);
	}
}
