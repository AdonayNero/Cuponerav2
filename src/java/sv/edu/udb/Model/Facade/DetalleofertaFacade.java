
package sv.edu.udb.Model.Facade;

import java.util.Date;
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
    
    public List<Detalleoferta> listbyEstado(){
        Query query = em.createQuery("SELECT d FROM Detalleoferta d where d.estado = 'activo'");
        return query.getResultList();
    }
    
    public void vendidos(int venta, int id){
        Query query = em.createQuery("UPDATE Detalleoferta d  SET d.venta = :venta WHERE d.idDetalle = :id");
        query.setParameter("venta", venta);
        query.setParameter("id", id);
        query.executeUpdate();
    }
    
    public void cambiarEstado(){
        Query query = em.createQuery("UPDATE Detalleoferta d  SET d.estado = \"inactivo\" WHERE d.venta = 0 or d.fechaFin < :fecha");
        query.setParameter("fecha", new Date());
        query.executeUpdate();
    }
    
}
