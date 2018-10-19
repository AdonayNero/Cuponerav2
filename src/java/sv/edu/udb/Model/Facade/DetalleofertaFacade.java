
package sv.edu.udb.Model.Facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.Model.Detalleoferta;

/**
 *
 * @author Manuel
 */
@Stateless
public class DetalleofertaFacade extends AbstractFacade<Detalleoferta> {

    @PersistenceContext(unitName = "Proyecto_Cuponera_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleofertaFacade() {
        super(Detalleoferta.class);
    }
    
    public List<Detalleoferta> listbyId(int id){
        Query query = em.createQuery("SELECT d FROM Detalleoferta d WHERE d.idDetalle = :idDetalle");
        query.setParameter("idDetalle", id);
        return query.getResultList();
    }
    
}
