/**
 * 
 */
package fit5042.controllers;

import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import fit5042.mbeans.IndustryTypeManagedBean;
import javax.inject.Named;
import fit5042.repository.entities.IndustryType;
import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 * @author user
 *
 */

@Named(value = "industryTypeApplication")
@ApplicationScoped
public class IndustryTypeApplication {
	
	@ManagedProperty(value="#{industryTypeManagedBean}") 
    IndustryTypeManagedBean industryTypeManagedBean;
	
	private ArrayList<IndustryType> industryTypes;
	
	private boolean showForm = true;
	
	public boolean isShowForm() {
        return showForm;
    }
	
	public IndustryTypeApplication() throws Exception {       
        industryTypes = new ArrayList<>();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        industryTypeManagedBean = (IndustryTypeManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "industryTypeManagedBean");
        
        updateIndustryTypeList();
    }
	
	public ArrayList<IndustryType> getIndustryTypes() {
        return industryTypes;
    }
	
	private void setIndustryTypes(ArrayList<IndustryType> newIndustryTypes) {
        this.industryTypes = newIndustryTypes;
    }
	
	public void updateIndustryTypeList() {
        if (industryTypes != null && industryTypes.size() > 0)
        {
            
        }
        else
        {
            industryTypes.clear();

            for (fit5042.repository.entities.IndustryType industryType : industryTypeManagedBean.getAllIndustryTypes())
            {
                industryTypes.add(industryType);
            }

            setIndustryTypes(industryTypes);
        }
    }

	public void searchIndustryTypeById(int industryTypeId) {
        industryTypes.clear();
        
        industryTypes.add(industryTypeManagedBean.searchIndustryTypeById(industryTypeId));
    }
	
	public void searchIndustryTypeByName(String industryTypeName) {
		industryTypes.clear();
        
		industryTypes.add(industryTypeManagedBean.searchIndustryTypeByName(industryTypeName));
    }	
	
	public void searchAll() {
        industryTypes.clear();
        
        for (fit5042.repository.entities.IndustryType industryType : industryTypeManagedBean.getAllIndustryTypes())
        {
            industryTypes.add(industryType);
        }
        
        setIndustryTypes(industryTypes);
    }
}
