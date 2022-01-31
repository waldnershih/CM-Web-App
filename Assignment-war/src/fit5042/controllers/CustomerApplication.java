/**
 * 
 */
package fit5042.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import fit5042.mbeans.CustomerManagedBean;
import javax.inject.Named;
import fit5042.repository.entities.Customer;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 * @author user
 *
 */

@Named(value = "customerApplication")
@ApplicationScoped
public class CustomerApplication {
	
	@ManagedProperty(value="#{customerManagedBean}") 
    CustomerManagedBean customerManagedBean;
	
	private final ArrayList<String> BUSINESS_PARTNERS = new ArrayList<String>(Arrays.asList("yes", "no"));
	
	private ArrayList<Customer> customers;
	
	private boolean showForm = true;
	
	public ArrayList<String> getBUSINESS_PARTNERS() {
		return BUSINESS_PARTNERS;
	}

	public boolean isShowForm() {
        return showForm;
    }
	
	public CustomerApplication() throws Exception {       
        customers = new ArrayList<>();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
        
        updateCustomerList();
    }
	
	public ArrayList<Customer> getCustomers() {
        return customers;
    }
	
	private void setCustomers(ArrayList<Customer> newCustomers) {
        this.customers = newCustomers;
    }
	
	public void updateCustomerList() {
		customers.clear();
		if (customerManagedBean.getAllCustomers()!=null) {
		    for (fit5042.repository.entities.Customer customer : customerManagedBean.getAllCustomers())
		    {
		    	customers.add(customer);
		    }
		    setCustomers(customers);
	    }
    }

	public void searchCustomerById(int customerId) {
		customers.clear();
	        
	    customers.add(customerManagedBean.searchCustomerById(customerId));		
    }
	
	public void searchCustomerByName(String customerName) {
		customers.clear();
	        
		customers.add(customerManagedBean.searchCustomerByName(customerName));	
    }	
	
	public void searchAll() {
		customers.clear();
	    for (fit5042.repository.entities.Customer customer : customerManagedBean.getAllCustomers())
	    {
	    	customers.add(customer);
	    }
	    setCustomers(customers);
    }
}
/**
customers.clear();
for (fit5042.repository.entities.Customer customer : customerManagedBean.searchCustomersByUser(customerManagedBean.getUser().getUserName()))
{
	customers.add(customer);
}
setCustomers(customers);
*/
