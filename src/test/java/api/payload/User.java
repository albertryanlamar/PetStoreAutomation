package api.payload;

public class User 
{
	 int id;
	 String username;
	 String firstName;
	 String lastName;
	 String email;
	 String password;
	 String phone;
	 int userStatus = 0;
	 
	public int getId() {
		return id;
	}
	public int setId(int id) {
		return this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public String setUsername(String username) {
		return this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public String setFirstName(String firstName) {
		return this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String setLastName(String lastName) {
		return this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public String setEmail(String email) {
		return this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public String setPassword(String password) {
		return this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public String setPhone(String phone) {
		return this.phone = phone;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public int setUserStatus(int userStatus) {
		 return this.userStatus = userStatus;
	}

}
