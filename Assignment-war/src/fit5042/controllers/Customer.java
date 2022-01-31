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

import fit5042.repository.entities.Address;
import fit5042.repository.entities.Contact;

/**
 * @author user
 *
 */
@RequestScoped
@Named(value = "customer")
public class Customer implements Serializable{
	private int customerId;
	private String customerBusinessPartner;
	private String customerEmail;
	private String customerName;
	private String customerNationality;
	private String customerPhoneNumber;
	private String industryType;	
	private String user;
	private String customerString;
	
	private Address address;
	private List<Contact> contacts;
	
	private String streetNumber;
    private String streetAddress;
    private String suburb;
    private String postcode;
    private String state;
    
    private Set<fit5042.repository.entities.Customer> customers;
    
    public Customer() {
    	contacts = new ArrayList<>();
    }

	public Customer(int customerId, String customerBusinessPartner, String customerEmail, String customerName,
			String customerNationality, String customerPhoneNumber, Address address, String industryType,
			String user) {
		this.customerId = customerId;
		this.customerBusinessPartner = customerBusinessPartner;
		this.customerEmail = customerEmail;
		this.customerName = customerName;
		this.customerNationality = customerNationality;
		this.customerPhoneNumber = customerPhoneNumber;
		this.address = address;
		this.industryType = industryType;
		this.user = user;
		contacts = new ArrayList<>();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerBusinessPartner() {
		return customerBusinessPartner;
	}

	public void setCustomerBusinessPartner(String customerBusinessPartner) {
		this.customerBusinessPartner = customerBusinessPartner;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNationality() {
		return customerNationality;
	}

	public void setCustomerNationality(String customerNationality) {
		this.customerNationality = customerNationality;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCustomerString() {
		return customerString;
	}

	public void setCustomerString(String customerString) {
		this.customerString = customerString;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<fit5042.repository.entities.Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<fit5042.repository.entities.Customer> customers) {
		this.customers = customers;
	}

}
