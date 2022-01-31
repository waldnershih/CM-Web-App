/**
 * 
 */
package fit5042.repository;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fit5042.repository.IndustryTypeRepository;
import fit5042.repository.entities.IndustryType;
import fit5042.repository.entities.Customer;
/**
 * @author user
 *
 */
@Stateless
public class JPAIndustryTypeRepositoryImpl implements IndustryTypeRepository {

	@PersistenceContext(unitName = "Assignment-ejbPU")
    private EntityManager entityManager;
	
	@Override
	public void addIndustryType(IndustryType industryType) throws Exception {
		List<IndustryType> industryTypes = entityManager.createNamedQuery(IndustryType.GET_ALL_QUERY_NAME).getResultList();
        industryType.setIndustryTypeId(industryTypes.get(0).getIndustryTypeId() + 1);
        entityManager.persist(industryType);		
        entityManager.flush();		
	}

	@Override
	public void editIndustryType(IndustryType industryType) throws Exception {
		try {
            entityManager.merge(industryType);
        } catch (Exception ex) {

        }			
	}

	@Override
	public List<IndustryType> getAllIndustryTypes() throws Exception {
		return entityManager.createNamedQuery(IndustryType.GET_ALL_QUERY_NAME).getResultList();
	}

	@Override
	public void removeIndustryType(int industryTypeId) throws Exception {
		IndustryType industryType = this.searchIndustryTypeById(industryTypeId);
    	if (industryType != null) {
    		entityManager.remove(industryType);
    	}		
	}

	@Override
	public IndustryType searchIndustryTypeById(int industryTypeId) throws Exception {
		IndustryType industryType = entityManager.find(IndustryType.class, industryTypeId);
	    return industryType;
	}

	@Override
	public IndustryType searchIndustryTypeByName(String industryTypeName) throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<IndustryType> cQuery = builder.createQuery(IndustryType.class);
    	Root<IndustryType> c = cQuery.from(IndustryType.class);
    	cQuery.select(c).where(builder.equal(c.get("industryTypeName"), industryTypeName));
        List<IndustryType> industryTypes = entityManager.createQuery(cQuery).getResultList();
        if (industryTypes != null) {
        	return industryTypes.get(0);
        }
    	return null;
	}
}
