/**
 * 
 */
package fit5042.mbeans;

import fit5042.repository.IndustryTypeRepository;
import fit5042.repository.entities.IndustryType;
import fit5042.repository.entities.Customer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author user
 *
 */
@ManagedBean(name = "industryTypeManagedBean")
@SessionScoped

public class IndustryTypeManagedBean implements Serializable{
	@EJB
    IndustryTypeRepository industryTypeRepository;
	
	/**
     * Creates a new instance of IndustryTypeManagedBean
     */
    public IndustryTypeManagedBean() {
    }
    /**
    public void addIndustryType(IndustryType industryType) {
        try {
             industryTypeRepository.addIndustryType(industryType);
        } catch (Exception ex) {
            Logger.getLogger(IndustryTypeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    public void editIndustryType(IndustryType industryType) {
        try {           
            industryTypeRepository.editIndustryType(industryType);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("IndustryType has been updated succesfully"));
        } catch (Exception ex) {
            Logger.getLogger(IndustryTypeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<IndustryType> getAllIndustryTypes() {
        try {
            return industryTypeRepository.getAllIndustryTypes();
        } catch (Exception ex) {
            Logger.getLogger(IndustryTypeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void removeIndustryType(int industryTypeId) {
        try {
            industryTypeRepository.removeIndustryType(industryTypeId);
        } catch (Exception ex) {
            Logger.getLogger(IndustryTypeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public IndustryType searchIndustryTypeById(int industryTypeId) {
        try {
            return industryTypeRepository.searchIndustryTypeById(industryTypeId);
        } catch (Exception ex) {
            Logger.getLogger(IndustryTypeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public IndustryType searchIndustryTypeByName(String industryTypeName) {
        try {
            return industryTypeRepository.searchIndustryTypeByName(industryTypeName);
        } catch (Exception ex) {
            Logger.getLogger(IndustryTypeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    } 
    
    public void addIndustryType(fit5042.controllers.IndustryType localIndustryType) {
        IndustryType industryType = convertIndustryTypeToEntity(localIndustryType);

        try {
            industryTypeRepository.addIndustryType(industryType);
        } catch (Exception ex) {
            Logger.getLogger(IndustryTypeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private IndustryType convertIndustryTypeToEntity(fit5042.controllers.IndustryType localIndustryType){
        IndustryType industryType = new IndustryType();
        industryType.setIndustryTypeId(localIndustryType.getIndustryTypeId());
        industryType.setIndustryTypeName(localIndustryType.getIndustryTypeName());
        return industryType;
    }
}
