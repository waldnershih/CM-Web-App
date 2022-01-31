package fit5042.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import fit5042.mbeans.IndustryTypeManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Hsuan-Yu Shih
 */
@RequestScoped
@Named("removeIndustryType")
public class RemoveIndustryType {
    @ManagedProperty(value="#{industryTypeManagedBean}") 
    IndustryTypeManagedBean industryTypeManagedBean;
    
    private boolean showForm = true;
    private IndustryType industryType;
    IndustryTypeApplication app;
    
    public void setIndustryType(IndustryType industryType){
        this.industryType = industryType;
    }
    
    public IndustryType getIndustryType(){
        return industryType;
    }
    
    public boolean isShowForm() {
        return showForm;
    }

    public RemoveIndustryType() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (IndustryTypeApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "industryTypeApplication");
        
        app.updateIndustryTypeList();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        industryTypeManagedBean = (IndustryTypeManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "industryTypeManagedBean");
    }

    public void removeIndustryType(int industryTypeId) {
       try
       {
            industryTypeManagedBean.removeIndustryType(industryTypeId);
            
            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("IndustryType has been deleted succesfully"));     
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;
    }
}

