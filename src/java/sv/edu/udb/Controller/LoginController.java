/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Controller;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        return "Index";
    }
    
    public Usuario getAuthUser(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        
        
        Usuario tmp = (Usuario)session.getAttribute("usuario");
        if(tmp != null){
            return tmp;
        }
        
        try {
            context.getExternalContext().redirect(request.getContextPath()+"/faces/Login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Usuario();
    }
    
    public Usuario getSessionStart(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        
        
        Usuario tmp = (Usuario)session.getAttribute("usuario");
        if(tmp != null){
            return tmp;
        }
        return null;
    }
    
    
    public void logout(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        
        session.removeAttribute("usuario");
        
        this.usuario = new Usuario();
        
        try {
            context.getExternalContext().redirect(request.getContextPath()+"/faces/Index.xhtml");
        } catch (IOException ex) {
            try {
                context.getExternalContext().redirect(request.getContextPath()+"/faces/Index.xhtml");
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex1) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
       

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    
}
