package api.payload;

public class order {

int id;
int petId;
int quantity;
String shipDate;
String status;
String complete;

    /*Setting id of order*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*End setting id of order*/

public int getPetId() {
	return petId;
}
public void setPetId(int petId) {
	this.petId = petId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public String getShipDate() {
	return shipDate;
}
public void setShipDate(String shipDate) {
	this.shipDate = shipDate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getComplete() {
	return complete;
}
public void setComplete(String complete) {
	this.complete = complete;
}


}
