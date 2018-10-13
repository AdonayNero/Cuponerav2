
package sv.edu.udb.Controller;

import java.util.List;
import java.util.Random;
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
        String codigo = this.genCodigo();
        usuario.setCodUsuario(codigo);
        usuario.setEstado("inactivo");
        usuario.setToken(this.genToken());
        
        usuarioFacade.create(usuario);
        usuario = new Usuario();
        roles = new Roles();
        usuario.setTipoAcceso(roles);
        return "GetUsuario";
    }
    public String findById(Usuario u){
        usuario = usuarioFacade.find(u.getCodUsuario());
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
    
    public String genCodigo(){
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int aleatorio = rnd.nextInt(9000);
        String codigo = "US"+aleatorio;
        return codigo;
    }
    
    public String genToken(){
        String token = "";
        int a;
    for (int i = 0; i < 7; i++) {
        if (i < 4) {    // 0,1,2,3 posiciones de numeros
            token = (int) (Math.random() * 5) + "" + token;

        } else {       // 4,5,6 posiciones de letras
            do {
                a = (int) (Math.random() * 26 + 65);///
            } while (a == 65 || a == 69 || a == 73 || a == 79 || a == 85);

            char letra = (char) a;
            if (i == 4) {
                token = token + letra;
            } else {
                token = token + "" + letra;
            }

        }
    }
    return token;
    }
    
    
    
    
}
