
package sv.edu.udb.Model.Facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.Model.Usuario;

/**
 *
 * @author Manuel
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "Proyecto_Cuponera_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public List<Usuario> usuarioByEncargado(String cod){
        Query query = em.createQuery("SELECT u FROM Usuario u Where u.codUsuario LIKE  :codigo");
        query.setParameter("codigo", cod+"%");
        return query.getResultList();
    }
    
}
