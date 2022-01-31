/**
 * 
 */
package fit5042.repository;

import java.util.List;
import javax.ejb.Remote;

import fit5042.repository.entities.*;
/**
 * @author user
 *
 */
@Remote
public interface CustomerRepository {
	
    public void addCustomer(Customer customer) throws Exception;
   
    public void editCustomer(Customer customer) throws Exception;
   
    public List<Customer> getAllCustomers() throws Exception;

    public void removeCustomer(int customerId) throws Exception;
  
    public Customer searchCustomerById(int customerId) throws Exception;

    public List<Customer> searchCustomersByUser(User user) throws Exception;
    
    // customer's name is impossible for repeat
    public Customer searchCustomerByName(String customerName) throws Exception;
      
    //public void assignCustomerToUser(Customer customer, User user) throws Exception;
    
    public List<Customer> searchCustomersByIndustryType(IndustryType industryType) throws Exception;

}
