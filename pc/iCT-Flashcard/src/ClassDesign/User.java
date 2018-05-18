package ClassDesign;

public class User {
	private String username;
	private String password;
	private String fullname;
	private String description;
	private String email;
	private String token;
	
	private static User instance;
	
	public static User getUser() {
		if(instance == null) {
			instance = new User();
		}
		return instance;
	}
	
	public String getUsername() {
		return instance.username;
	}
	public void setUsername(String username) {
		instance.username = username;
	}
	public String getFullname() {
		return instance.fullname;
	}
	public void setFullname(String fullname) {
		instance.fullname = fullname;
	}
	public String getPassword() {
		return instance.password;
	}
	public void setPassword(String password) {
		instance.password = password;
	}
	public String getDescription() {
		return instance.description;
	}
	public void setDescription(String description) {
		instance.description = description;
	}
	public String getEmail() {
		return instance.email;
	}
	public void setEmail(String email) {
		instance.email = email;
	}
	public String getToken() {
		return instance.token;
	}
	public void setToken(String token) {
		instance.token = token;
	}
	
}
