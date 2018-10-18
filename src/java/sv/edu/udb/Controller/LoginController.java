/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sv.edu.udb.Model.Facade.UsuarioFacade;
import sv.edu.udb.Model.Usuario;

/**
 *
 * @author Edwin Bonilla
 */
@Named(value = "login")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    
    private Usuario usuario;
    /**
     * Creates a new instance of login
     */
    public LoginController() {
        usuario = new Usuario();
    }
    
    public String autenticar(){
        
        
        Usuario resultado = usuarioFacade.Login(usuario);
        
        if(resultado == null){
            return "Login";
        }
        
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        
        session.setAttribute("usuario", resultado);
        
        this.usuario = resultado;
        return "ConfirmLogout";
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
    
    
}
