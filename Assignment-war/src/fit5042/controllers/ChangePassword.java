package fit5042.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("changePassword")
public class ChangePassword {
	private String firstPassword;
	private String secondPassword;
	
	public ChangePassword() {
	}

	public ChangePassword(String firstPassword, String secondPassword) {
		this.firstPassword = firstPassword;
		this.secondPassword = secondPassword;
	}

	public String getFirstPassword() {
		return firstPassword;
	}

	public void setFirstPassword(String firstPassword) {
		this.firstPassword = firstPassword;
	}

	public String getSecondPassword() {
		return secondPassword;
	}

	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}

}
