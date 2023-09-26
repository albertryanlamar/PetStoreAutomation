package api.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.github.javafaker.Faker;

import api.endpoints.storeEndpoints;
import api.payload.order;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class storeTest {
	
	Faker faker;
	order orderPayload;
	int getID;
	
	/*
	@BeforeClass
	public void orderData()
	{
		faker = new Faker();
		orderPayload = new order();

		orderPayload.setId(1);
		orderPayload.setPetId(faker.idNumber().hashCode());
		orderPayload.setQuantity(faker.number().hashCode());
		orderPayload.setShipDate("2023-09-10T09:30:06.406Z");//(faker.date().toString());
		orderPayload.setStatus("placed");
		orderPayload.setComplete("true");
	}
	*/
	
	
	@Test (priority  = 1)
	public void createorder() throws FileNotFoundException 
	{
        File f = new File("./src/test/java/api/payload/order.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject ob = new JSONObject(jt);
        
	  Response response =  storeEndpoints.creatOrder(ob);
	  response.then()
	  .statusCode(200)
	  .log().all();
	  getID = response.jsonPath().getInt("id");
	  System.out.println(getID);
	}
	@Test (priority =2)
	public void getorder()
	{
		System.out.println(getID);
		Response response  = storeEndpoints.getOrder(getID);
		response.then()
		//.statusCode(200);
		.log().all();
	}
	
	@Test (priority =3)
	public void getorderInventory()
	{
		Response response  = storeEndpoints.getInventory();
		response.then()
		.statusCode(200)
		.log().all();
	}
}
