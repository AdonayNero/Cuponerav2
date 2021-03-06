
package sv.edu.udb.Controller;

import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.taglibs.standard.tag.common.core.ParamSupport;
import sv.edu.udb.Model.Facade.RolesFacade;
import sv.edu.udb.Model.Facade.UsuarioFacade;
import sv.edu.udb.Model.Roles;
import sv.edu.udb.Model.Usuario;
import sv.edu.udb.util.Correo;

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
    private LoginController login = new LoginController();
    
    public UsuarioController() {
        usuario = new Usuario();
        roles = new Roles();
        usuario.setTipoAcceso(roles);
    }
    
     // Metodo para listar Usuario
    public List<Usuario> getUsuarioList() {
        return usuarioFacade.findAll();
    }
    
    public List<Usuario> getUsuarioListEng() {
       
        return usuarioFacade.usuarioByEncargado(login.getAuthUser().getCodUsuario());
        //return usuarioFacade.findAll();
    }
    
    public String create(){
        String codigo = this.genCodigo();
        if (login.getSessionStart() != null) {
            usuario.setCodUsuario(genCodDep());
        }else{
            usuario.setCodUsuario(codigo);
        }
        
        usuario.setEstado("inactivo");
        usuario.setToken(this.genToken());
        
        usuarioFacade.create(usuario);
        
        //validacion de correo
         String cadenaAleatoria = usuario.getToken();
        
        
         Correo correo = new Correo();
                    //Defino los valores de los atributos del correo
                    correo.setAsunto("Confirmacion de registro");
                    correo.setMensaje("Prueba de envio de correo  usando ya el proyecto <html><a href='http://localhost:8080/Cuponerav3/faces/Usuario/okUsuario.xhtml?token="+cadenaAleatoria+"'>Enlace</a></html>");
                    correo.setDestinatario(usuario.getEmail());
                    
                    //Finalmente envio el correo
                    correo.enviarCorreo();
        usuario = new Usuario();
        roles = new Roles();
        usuario.setTipoAcceso(roles);
       
        
        return "../Index?faces-redirect=true";
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
    
    public String genCodDep(){
        String token = "";
        int a;
    for (int i = 0; i < 7; i++) {
        if (i < 3) {    // 0,1,2,3 posiciones de numeros
           
            do {
                a = (int) (Math.random() * 26 + 65);///
            } while (a == 65 || a == 69 || a == 73 || a == 79 || a == 85);

            char letra = (char) a;
           
                token = token + letra;
           
        }
    }
    return login.getAuthUser().getCodUsuario()+token;
    }
    
   public String okToken(){
       FacesContext facesContext = FacesContext.getCurrentInstance();
       ExternalContext externalContext = facesContext.getExternalContext();
       
       Map params = externalContext.getRequestParameterMap();
       String codigo = (String) params.get("token");
       usuarioFacade.confirmarUsuario(codigo);
        return "GetUsuario";
       
   }
    
}
