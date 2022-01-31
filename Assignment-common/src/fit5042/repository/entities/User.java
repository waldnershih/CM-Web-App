package fit5042.repository.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQueries({
    @NamedQuery(name=User.GET_ALL_QUERY_NAME, query="SELECT u FROM User u")})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_QUERY_NAME = "User.getAll";	

	private int userId;
	private String userName;
	private String userPassword;
	private String userType;	
	
	private List<Contact> contacts;
	private List<Customer> customers;

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

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="USER_ID")
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name="USER_NAME")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="USER_PASSWORD")
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name="USER_TYPE")
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@OneToMany(mappedBy="user", cascade = CascadeType.REMOVE)
	public List<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contact addContact(Contact contact) {
		getContacts().add(contact);
		contact.setUser(this);

		return contact;
	}

	public Contact removeContact(Contact contact) {
		getContacts().remove(contact);
		contact.setUser(null);

		return contact;
	}
	
	@OneToMany(mappedBy="user", cascade = CascadeType.REMOVE)
	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	// add customer into customer list
	// add fk
	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setUser(this);

		return customer;
	}
	
	// remove customer from customer list
	// remove fk
	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setUser(null);

		return customer;
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.userId;
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
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }
	
	@Override
	public String toString() {
	  	return userName;
  	}	
}