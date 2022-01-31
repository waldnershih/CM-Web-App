/**
 * 
 */
package fit5042.repository.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author Hsuan-Yu Shih
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Customer.GET_ALL_QUERY_NAME, query = "SELECT c FROM Customer c")})
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_QUERY_NAME = "Customer.getAll";	
	
	private int customerId;
	private String customerBusinessPartner;
	private String customerEmail;
	private String customerName;
	private String customerNationality;
	private String customerPhoneNumber;
	
	private Address address;
	private List<Contact> contacts;
	private IndustryType industryType;	
	private User user;

	public Customer() {
		contacts = new ArrayList<>();
	}

	public Customer(int customerId, String customerBusinessPartner, String customerEmail, String customerName,
			String customerNationality, String customerPhoneNumber, Address address, IndustryType industryType,
			User user) {
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

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="CUSTOMER_ID")
	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Column(name="CUSTOMER_BUSINESS_PARTNER")
	public String getCustomerBusinessPartner() {
		return this.customerBusinessPartner;
	}

	public void setCustomerBusinessPartner(String customerBusinessPartner) {
		this.customerBusinessPartner = customerBusinessPartner;
	}

	@Column(name="CUSTOMER_EMAIL")
	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	@Column(name="CUSTOMER_NAME")
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name="CUSTOMER_NATIONALITY")
	public String getCustomerNationality() {
		return this.customerNationality;
	}

	public void setCustomerNationality(String customerNationality) {
		this.customerNationality = customerNationality;
	}

	@Column(name="CUSTOMER_PHONE_NUMBER")
	public String getCustomerPhoneNumber() {
		return this.customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	@Embedded
	//@PostConstruct
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
	public List<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@ManyToOne
	//@JoinColumn(name="INDUSTRY_TYPE_ID")
	public IndustryType getIndustryType() {
		return this.industryType;
	}

	public void setIndustryType(IndustryType industryType) {
		this.industryType = industryType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Contact addContact(Contact contact) {
		getContacts().add(contact);
		contact.setCustomer(this);

		return contact;
	}

	public Contact removeContact(Contact contact) {
		getContacts().remove(contact);
		contact.setCustomer(null);

		return contact;
	}
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.customerId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.customerId != other.customerId) {
            return false;
        }
        return true;
    }
	
	@Override
		public String toString() {
		  	return customerName;
	  	}
    
}
