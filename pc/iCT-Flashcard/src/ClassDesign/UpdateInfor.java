package ClassDesign;

public class UpdateInfor {
	private String username;
	private String token;
	private String oldPassword;
	private User newUserInfo;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getNewUserInfo() {
		return newUserInfo;
	}
	public void setNewUserInfo(User newUserInfo) {
		this.newUserInfo = newUserInfo;
	}

}
