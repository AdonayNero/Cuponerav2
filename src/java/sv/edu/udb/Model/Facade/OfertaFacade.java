
package sv.edu.udb.Model.Facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.Model.Oferta;

/**
 *
 * @author Manuel
 */
@Stateless
public class OfertaFacade extends AbstractFacade<Oferta> {

    @PersistenceContext(unitName = "Proyecto_Cuponera_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OfertaFacade() {
        super(Oferta.class);
    }
    
    public List<Oferta> ofertaByEnc(String cod){
        Query query = em.createQuery("SELECT o FROM Oferta o Where o.codOferta LIKE :codigo");
        query.setParameter("codigo", cod+"%");
        return query.getResultList();
    }
    public List<Oferta> ofertaByEstado(){
        Query query = em.createQuery("SELECT o FROM Oferta o WHERE o.estado = :estado");
        query.setParameter("estado", "activo");
        return query.getResultList();
    }
    
}