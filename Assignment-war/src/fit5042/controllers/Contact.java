/**
 * 
 */
package fit5042.controllers;

import java.io.Serializable;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author user
 *
 */
@RequestScoped
@Named(value = "contact")
public class Contact implements Serializable{
	private int contactId;
	private String contactPhoneNumber;
	private String contactEmail;
	private String contactGender;
	private String contactName;
	private String customer;
	private String user;
	
	private Set<fit5042.repository.entities.Contact> contacts;	
	
	public Contact() {
    }

	public Contact(int contactId, String contactPhoneNumber, String contactEmail, String contactGender,
			String contactName, String customer, String user) {
		this.contactId = contactId;
		this.contactPhoneNumber = contactPhoneNumber;
		this.contactEmail = contactEmail;
		this.contactGender = contactGender;
		this.contactName = contactName;
		this.customer = customer;
		this.user = user;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactGender() {
		return contactGender;
	}

	public void setContactGender(String contactGender) {
		this.contactGender = contactGender;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Set<fit5042.repository.entities.Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<fit5042.repository.entities.Contact> contacts) {
		this.contacts = contacts;
	}
	
}
