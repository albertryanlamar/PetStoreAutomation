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
import api.validation.*;


public class storeTest {
	
	Faker faker;
	order orderPayload;
	int getID;
	storeTestValidation storevalidate = new storeTestValidation();
	
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
        File f = new File("./src/test/java/api/payload/order.json");//read or get the file on the folder
        FileReader fr = new FileReader(f); // initiate file reader, to read the file
        JSONTokener jt = new JSONTokener(fr); // cast the file into json by jsontokener, the file was string casting to json format since API Read/accept only jsonformat
        JSONObject ob = new JSONObject(jt); // cast the json into object since java is oop language, meaning accept only object
        
	  Response response =  storeEndpoints.creatOrder(ob);//call the create order function method and assign the object ob as method parameter
	  storevalidate.creatorderValidation(response);//call the function of store validation
	  getID = response.jsonPath().getInt("id"); //get the API response ID and assign or store the value in getID variable
	}
	@Test (priority =2)
	public void getorder()
	{
		Response response  = storeEndpoints.getOrder(getID);
		storevalidate.getorderValidation(response);//call the function of validation
	}
	
	@Test (priority =3)
	public void getorderInventory()
	{
		Response response  = storeEndpoints.getInventory();
		storevalidate.getinventoryValidation(response);
	}
}
