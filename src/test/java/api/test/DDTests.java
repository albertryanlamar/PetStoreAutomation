package api.test;

import static org.hamcrest.Matchers.is;

import org.testng.Assert;
import api.utilities.Dataproviders;
import org.testng.annotations.Test;

import api.endpoints.userEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class DDTests
{
	@Test(priority = 1, dataProvider ="Data", dataProviderClass = Dataproviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname,String useremail, String pwd, String ph)
	{
		User userPayload = new User();
		

		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setEmail(pwd);
		userPayload.setPhone(ph);
		
		Response response = userEndpoints.createUser(userPayload);
		String messag = response.jsonPath().getString("message");
		response.then()
			.assertThat()
			.body("type", is("unknown"))
			.body("message",is(messag))
			.log().all();
		    Assert.assertEquals(response.getStatusCode(), 200);
		    System.out.println("Id Number 3: " + userPayload.getId());
	}
	
	@Test(priority = 2, dataProvider ="UserNames", dataProviderClass = Dataproviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response response = userEndpoints.delUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
