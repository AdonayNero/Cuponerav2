
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
    
    public List<Detalleoferta> listbyCod(String cod){
        Query query = em.createQuery("SELECT d From Detalleoferta d where d.codOferta.codOferta like :cod");
        query.setParameter("cod", cod+"%");
        return query.getResultList();
    }
    
    public List<Detalleoferta> listbySearch(String cod){
        Query query = em.createQuery("SELECT d FROM Detalleoferta d, d.idSucusal s, d.codOferta o, d.idCategoria c where s.direccion like :cod or o.titulo like :cod or o.valorOferta like :cod or c.nombre like :cod");
        query.setParameter("cod", "%"+cod+"%");
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
        Query query = em.createQuery("UPDATE Detalleoferta d  SET d.estado = \"inactivo\" WHERE d.venta = 0 or d.fechaFin < :fecha and d.estado = 'activo'");
        query.setParameter("fecha", new Date());
        query.executeUpdate();
        
        
        
        
    }
    public void activarOferta(){
        Query query2 = em.createQuery("UPDATE Detalleoferta d  SET d.estado = 'activo' WHERE d.venta > 0 and d.fechaInicio =:fecha and d.estado = 'inactivo'");
        query2.setParameter("fecha", new Date());
        query2.executeUpdate();
    }
    
    public void cambiarEstado(int id){
        Query query = em.createQuery("UPDATE Detalleoferta d  SET d.estado = \"inactivo\" WHERE d.idDetalle = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }
    public int countOfertaRevision(){
        Query query = em.createQuery("select count(d) from Detalleoferta d where d.estado='revision'");
        
        return (int)query.getSingleResult();
    }
}
