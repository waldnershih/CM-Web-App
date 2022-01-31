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
public interface UserRepository {
	
    public void addUser(User user) throws Exception;
   
    public void editUser(User user) throws Exception;
   
    public List<User> getAllUsers() throws Exception;

    public void removeUser(int userId) throws Exception;
  
    public User searchUserById(int userId) throws Exception;

    public User searchUserByName(String userName) throws Exception;

}