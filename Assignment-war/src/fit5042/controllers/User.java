/**
 * 
 */
package fit5042.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import fit5042.repository.entities.Customer;

/**
 * 
 * @author user
 *
 */
@RequestScoped
@Named(value = "user")
public class User implements Serializable{
	private int userId;
	private String userName;
	private String userPassword;
	private String userType;
	private String userString;
	
	private List<Contact> contacts;
	private List<Customer> customers;
	
	private Set<fit5042.repository.entities.User> users;
	
	public User() {
		contacts = new ArrayList<>();
		customers = new ArrayList<>();
	}

	public User(int userId, String userName, String userPassword, String userType) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userType = userType;
		contacts = new ArrayList<>();
		customers = new ArrayList<>();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public String getUserString() {
		return userString;
	}

	public void setUserString(String userString) {
		this.userString = userString;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Set<fit5042.repository.entities.User> getUsers() {
		return users;
	}

	public void setUsers(Set<fit5042.repository.entities.User> users) {
		this.users = users;
	}
	
}
