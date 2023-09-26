package api.endpoints;

import api.payload.User;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

//userEndpoints.java
//Created for perform CRUD request the user API
public class userEndpoints 
{
	public static Response createUser(User payload) 
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		return response;
	}
	
	public static Response getUser(String userName) 
	{
		Response response = given()
				.pathParam("username", userName)
				
			.when()
				.get(Routes.get_url);
	    return response;			
	}
	
	public static Response updateUser(String userName,User payload) 
	{
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			.when()
				.put(Routes.update_url);
	     return response;
	}
	
	public static Response delUser(String userName) 
	{
		Response response = given()
				.pathParam("username", userName)
			.when()
				.delete (Routes.delete_url);
	     return response;
	}
}
