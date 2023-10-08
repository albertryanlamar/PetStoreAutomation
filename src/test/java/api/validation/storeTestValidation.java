package api.validation;

import io.restassured.response.Response;

public class storeTestValidation 
{
	public void creatorderValidation(Response response) 
	{
		  response.then()
		  .statusCode(200)
		  .log().all();
	}
	
	public void getorderValidation(Response response) 
	{
		  response.then()
		  .statusCode(200)
		  .log().all();
	}
	public void getinventoryValidation(Response response) 
	{
		  response.then()
		  .statusCode(200)
		  .log().all();
	}
}
