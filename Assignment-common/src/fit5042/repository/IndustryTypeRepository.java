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
public interface IndustryTypeRepository {
	
    public void addIndustryType(IndustryType industryType) throws Exception;
   
    public void editIndustryType(IndustryType industryType) throws Exception;
   
    public List<IndustryType> getAllIndustryTypes() throws Exception;

    public void removeIndustryType(int industryTypeId) throws Exception;
  
    public IndustryType searchIndustryTypeById(int industryTypeId) throws Exception;

    // name is impossible for repeat
    public IndustryType searchIndustryTypeByName(String industryTypeName) throws Exception;
}