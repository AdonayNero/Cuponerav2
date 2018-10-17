
package sv.edu.udb.Model.Facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.Model.Empresa;

/**
 *
 * @author Manuel
 */
@Stateless
public class EmpresaFacade extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "Proyecto_Cuponera_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }
    
    public List<Empresa> empresaByEncargado(String cod){
        Query query = em.createQuery("SELECT e FROM Empresa e WHERE e.encargado = :encargado");
        query.setParameter("encargado", cod);
        return query.getResultList();
    }
    
}
