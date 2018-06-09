package ClassDesign;

public class User {
	private String username;
	private String password;
	private String fullname;
	private String bio;
	private String email;
	private String token;
	private String profile_photo;

	
	private static User instance = new User();
	
	private User() {}
	
	public static User getUser() {
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

	public String getBio() {
		return instance.bio;
	}

	public void setBio(String bio) {
		instance.bio = bio;
	}

	public String getProfile_photo() {
		return instance.profile_photo;
	}

	public void setProfile_photo(String profile_photo) {
		instance.profile_photo = profile_photo;
	}


}
