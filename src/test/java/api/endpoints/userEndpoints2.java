package api.endpoints;

import api.payload.User;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;

//userEndpoints.java
//Created for perform CRUD request the user API
public class userEndpoints2 
{
	/* method for getting URL's from properties file */
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");//load property file
	return routes;
	}
	/* End of method for getting URL's from properties file */
	
	public static Response createUser(User payload) 
	{
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(getURL().getString("post_url"));
		return response;
	}
	
	public static Response getUser(String userName) 
	{
		Response response = given()
				.pathParam("username", userName)
				
			.when()
				.get(getURL().getString("get_url"));
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
				.put(getURL().getString("update_url"));
	     return response;
	}
	
	public static Response delUser(String userName) 
	{
		Response response = given()
				.pathParam("username", userName)
			.when()
				.delete (getURL().getString("delete_url"));
	     return response;
	}
}
