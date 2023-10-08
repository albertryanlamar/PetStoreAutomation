package api.validation;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.testng.Assert;

import api.endpoints.userKeys;
import io.restassured.response.Response;
import api.endpoints.userKeys;
import api.payload.User;

public class userTestValidation 
{
	
	public void postuserValidation(Response response) 
	{
		String messag = response.jsonPath().getString("message");
		
		response.then()
		.assertThat()
		.body("type", is("unknown"))
		.body("message",is(messag))
		.log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//or
		// Assert.assertEquals(response.getBody().jsonPath().get("type"), "unknown");
		// Assert.assertEquals(response.getBody().jsonPath().get("message"), "messag");
	}
	
	public void getuserValidation(Response response, User userPayload, int fke_idNumber) 
	{
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
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(fke_idNumber,userPayload.getId());
	}
	
	public void updateuserValidation(Response response) 
	{
		String messag = response.jsonPath().getString("message");
		response.then()
			.assertThat()
			.body("type", is("unknown"))
			.body("message",is(messag))
			.log().all();
			 Assert.assertEquals(response.getStatusCode(), 200);
	}
}
