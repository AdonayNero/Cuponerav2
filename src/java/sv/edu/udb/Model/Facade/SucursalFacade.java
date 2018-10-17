
package sv.edu.udb.Model.Facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.Model.Sucursal;

/**
 *
 * @author Manuel
 */
@Stateless
public class SucursalFacade extends AbstractFacade<Sucursal> {

    @PersistenceContext(unitName = "Proyecto_Cuponera_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SucursalFacade() {
        super(Sucursal.class);
    }
    
    public List<Sucursal> SucursalByEncargado(String cod){
        Query query = em.createQuery("SELECT s FROM Sucursal s Where s.dependiente LIKE :codEncargado");
        query.setParameter("codEncargado", cod+"%");
        return query.getResultList();
    }
    
}
