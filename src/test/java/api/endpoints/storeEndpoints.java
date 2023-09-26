package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import api.payload.order;

public class storeEndpoints {

	public static Response creatOrder(Object payload) {
	 Response response = given()
			 .contentType(ContentType.JSON)
			 .accept(ContentType.JSON)
			 .body(payload.toString())
			 .when()
			 .post(Routes.post_order_url);
		return response;
	}
	
	public static Response getOrder(int orderID) {
		
		Response response = given()
				 .pathParam("orderid",orderID)
				 .when()
				 .get(Routes.get_order_url);
		return response;
	}
	
	public static Response getInventory() {
		
		Response response = given()
				 .when()
				 .get(Routes.get_storeinventory_url);
		return response;
	}
	
	public static Response deleteOrder(String orderID) {
		
		Response response = given()
				 .param("orderID",orderID)
				 .when()
				 .delete(Routes.delete_order_url);
		return response;
	}
	
}
