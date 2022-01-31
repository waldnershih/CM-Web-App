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
 * @author user
 *
 */
@RequestScoped
@Named(value = "industryType")
public class IndustryType implements Serializable{
	private int industryTypeId;
	private String industryTypeName;
	private String industryTypeString;
	
	private List<Customer> customers;
	
	private Set<fit5042.repository.entities.IndustryType> industryTypes;
	
	public IndustryType() {
		customers = new ArrayList<>();
	}

	public IndustryType(int industryTypeId, String industryTypeName) {
		this.industryTypeId = industryTypeId;
		this.industryTypeName = industryTypeName;
		customers = new ArrayList<>();
	}

	public int getIndustryTypeId() {
		return industryTypeId;
	}

	public void setIndustryTypeId(int industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public String getIndustryTypeName() {
		return industryTypeName;
	}

	public void setIndustryTypeName(String industryTypeName) {
		this.industryTypeName = industryTypeName;
	}

	public String getIndustryTypeString() {
		return industryTypeString;
	}

	public void setIndustryTypeString(String industryTypeString) {
		this.industryTypeString = industryTypeString;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Set<fit5042.repository.entities.IndustryType> getIndustryTypes() {
		return industryTypes;
	}

	public void setIndustryTypes(Set<fit5042.repository.entities.IndustryType> industryTypes) {
		this.industryTypes = industryTypes;
	}
}
