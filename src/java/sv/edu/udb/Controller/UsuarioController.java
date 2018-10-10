
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sv.edu.udb.Model.Facade.RolesFacade;
import sv.edu.udb.Model.Facade.UsuarioFacade;
import sv.edu.udb.Model.Roles;
import sv.edu.udb.Model.Usuario;

/**
 *
 * @author Manuel
 */
@Named(value = "usuarioController")
@RequestScoped
public class UsuarioController {

    @EJB
    UsuarioFacade usuarioFacade;
    
    @EJB
    RolesFacade rolesFacade;
    
    private List<Usuario> usuarioList;
    
    private Usuario usuario;
    private Roles roles;
    
    public UsuarioController() {
        usuario = new Usuario();
        roles = new Roles();
        usuario.setTipoAcceso(roles);
    }
    
     // Metodo para listar Usuario
    public List<Usuario> getUsuarioList() {;
        return usuarioFacade.findAll();
    }
    
    // Metodo para listar Empresa
    public List<Roles> getRolesList(){
        return rolesFacade.findAll();
    }
    
}
