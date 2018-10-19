
package sv.edu.udb.Model.Facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.Model.Venta;

/**
 *
 * @author Manuel
 */
@Stateless
public class VentaFacade extends AbstractFacade<Venta> {

    @PersistenceContext(unitName = "Proyecto_Cuponera_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaFacade() {
        super(Venta.class);
    }
    
    public List<Venta> ventaByUsr(String cod){
        Query query = em.createQuery("SELECT v FROM Venta v WHERE v.codCliente = :codC");
        query.setParameter("codC", cod);
        return query.getResultList();
    }
    
}
