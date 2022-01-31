/**
 * 
 */
package fit5042.repository.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * @author Hsuan-Yu Shih
 *
 */

@Entity
@Table(name = "INDUSTRY_TYPE")
@NamedQueries({
    @NamedQuery(name = IndustryType.GET_ALL_QUERY_NAME, query = "SELECT i FROM IndustryType i")})
public class IndustryType implements Serializable{
	//private static final long serialVersionUID = 1L;
	public static final String GET_ALL_QUERY_NAME = "IndustryType.getAll";	
	
	private int industryTypeId;
	private String industryTypeName;
	
	private List<Customer> customers;

	public IndustryType() {
		customers = new ArrayList<>();
	}
	
	public IndustryType(int industryTypeId, String industryTypeName) {
		this.industryTypeId = industryTypeId;
		this.industryTypeName = industryTypeName;
		customers = new ArrayList<>();
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="INDUSTRY_TYPE_ID")
	public int getIndustryTypeId() {
		return this.industryTypeId;
	}

	public void setIndustryTypeId(int industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	@Column(name="INDUSTRY_TYPE_NAME")
	public String getIndustryTypeName() {
		return this.industryTypeName;
	}

	public void setIndustryTypeName(String industryTypeName) {
		this.industryTypeName = industryTypeName;
	}

	@OneToMany(mappedBy="industryType", cascade = CascadeType.ALL )
	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setIndustryType(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setIndustryType(null);

		return customer;
	}
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.industryTypeId;
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
        final IndustryType other = (IndustryType) obj;
        if (this.industryTypeId != other.industryTypeId) {
            return false;
        }
        return true;
    }
	
	@Override
	public String toString() {
		return industryTypeName;
	}	
	
	
}
