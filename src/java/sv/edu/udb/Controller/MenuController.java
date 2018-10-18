/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlPanelGrid;
import org.w3c.dom.html.HTMLLinkElement;
import sv.edu.udb.Model.Usuario;

/**
 *
 * @author Edal Bonilla
 */
@Named(value = "menuController")
@SessionScoped
public class MenuController implements Serializable {
    
    private HtmlCommandButton prueba;
    
    private Usuario usr;
    private LoginController lgController;

    /**
     * Creates a new instance of MenuController
     */
    public MenuController() {
        usr = new Usuario();
        prueba.setDisabled(false);
        
    }
    
    public void control(){
        
            prueba.setDisabled(true);
    }

    public HtmlCommandButton getPrueba() {
        return prueba;
    }

    public void setPrueba(HtmlCommandButton prueba) {
        this.prueba = prueba;
    }
    
    
    
}
