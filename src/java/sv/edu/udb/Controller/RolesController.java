
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
    
    // Metodo para listar Empresa
    public List<Roles> getRolesList() {
        return rolesFacade.findAll();
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
    
    
    
}
