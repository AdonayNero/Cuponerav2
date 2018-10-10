
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    public String create(){
        usuarioFacade.create(usuario);
        usuario = new Usuario();
        roles = new Roles();
        usuario.setTipoAcceso(roles);
        return "GetUsuario";
    }
    public String findById(Usuario u){
        usuario = usuarioFacade.find(u.getCodUsuario());
        usuario = new Usuario();
        roles = new Roles();
        usuario.setTipoAcceso(roles);
        return "UpdateUsuario";
        
    }
    public String update(){
        usuarioFacade.edit(usuario);
        usuario = new Usuario();
        roles = new Roles();
        usuario.setTipoAcceso(roles);
        return "GetUsuario";
        
    }
    public void delete(Usuario u){
        usuarioFacade.remove(u);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminada", "Rol Eliminado");
        FacesContext.getCurrentInstance().addMessage(null,message);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
    
}
