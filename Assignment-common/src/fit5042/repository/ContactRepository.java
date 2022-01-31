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
public interface ContactRepository {
	
    public void addContact(Contact contact) throws Exception;
   
    public void editContact(Contact contact) throws Exception;
   
    public List<Contact> getAllContacts() throws Exception;

    public void removeContact(int contactId) throws Exception;
  
    public Contact searchContactById(int contactId) throws Exception;

    public List<Contact> searchContactsByCustomer(Customer customer) throws Exception;

    public List<Contact> searchContactsByName(String contactName) throws Exception;
    
    public List<Contact> searchContactsByUser(User user) throws Exception;
}


	
	
