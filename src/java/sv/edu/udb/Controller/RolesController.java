
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sv.edu.udb.Model.Facade.RolesFacade;
import sv.edu.udb.Model.Roles;

/**
 *
 * @author Manuel
 */
@Named(value = "rolesController")
@RequestScoped
public class RolesController {

    @EJB
    RolesFacade rolesFacade;
    
    private List<Roles> rolesList;
    
    private Roles roles;
    
    public RolesController() {
        roles = new Roles();
    }
    
    public String create(){
        rolesFacade.create(roles);
        roles = new Roles();
        
        return "GetRol";
    }
    
    // Metodo para listar Empresa
    public List<Roles> getRolesList() {
        return rolesFacade.findAll();
        
    }
    
    public String getById(Roles r){
        roles = rolesFacade.find(r.getIdRoles());
        return "UpdateRol";
    }
    public String update(){
        rolesFacade.edit(roles);
        roles = new Roles();
        return "GetRol";
    }
    
    public void delete(Roles r){
        rolesFacade.remove(r);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminada", "Rol Eliminado");
        FacesContext.getCurrentInstance().addMessage(null,message);
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
    
    
    
    
    
}
