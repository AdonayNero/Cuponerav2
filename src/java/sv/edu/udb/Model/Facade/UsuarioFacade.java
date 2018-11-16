
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
    
    public Usuario Login(Usuario user){
        Query query = em.createQuery("SELECT u FROM Usuario u Where u.email = :email and u.pass = :pass and u.estado = 'activo'");
        query.setParameter("email", user.getEmail());
        query.setParameter("pass", user.getPass());
        
        user = new Usuario();
        for (Object users : query.getResultList()) {
            user = (Usuario) users;
            return (Usuario) user;
        }
        return null;
    }
    //para validar el token
    public void confirmarUsuario(String cod){
        Query query = em.createQuery("UPDATE Usuario u  SET u.estado = \"activo\" WHERE u.token = :token");
        query.setParameter("token", cod);
        query.executeUpdate();
    }
    
}
