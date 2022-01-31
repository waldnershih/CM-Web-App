/**
 * 
 */
package fit5042.repository.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Hsuan-Yu Shih
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Contact.GET_ALL_QUERY_NAME, query = "SELECT c FROM Contact c")})
public class Contact implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_QUERY_NAME = "Contact.getAll";
		
	private int contactId;
	private String contactEmail;	
	private String contactName;	
	private String contactGender;	
	private String contactPhoneNumber;

	private Customer customer;
	private User user;

	public Contact() {
	}	
	
	public Contact(int contactId, String contactEmail, String contactName, String contactGender,
			String contactPhoneNumber, Customer customer, User user) {
		this.contactId = contactId;
		this.contactEmail = contactEmail;
		this.contactName = contactName;
		this.contactGender = contactGender;
		this.contactPhoneNumber = contactPhoneNumber;
		this.customer = customer;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="CONTACT_ID")
	public int getContactId() {
		return this.contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	@Column(name="CONTACT_EMAIL")
	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@Column(name="CONTACT_NAME")
	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Column(name="CONTACT_GENDER")
	public String getContactGender() {
		return this.contactGender;
	}

	public void setContactGender(String contactGender) {
		this.contactGender = contactGender;
	}

	@Column(name="CONTACT_PHONE_NUMBER")
	public String getContactPhoneNumber() {
		return this.contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	@ManyToOne 
	//@JoinColumn(name="CUSTOMER_ID")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne
	//@JoinColumn(name="USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
    public String toString() {
        return contactName + " - " + contactPhoneNumber;
    }
}
