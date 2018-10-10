
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sv.edu.udb.Model.Empresa;
import sv.edu.udb.Model.Facade.EmpresaFacade;

/**
 *
 * @author Manuel
 */
@Named(value = "empresaController")
@RequestScoped
public class EmpresaController {

    @EJB
    EmpresaFacade empresaFacade;
    
    private List<Empresa> empresaList;
    
    private Empresa empresa;
               
    public EmpresaController() {
        empresa = new Empresa();
    }
    
    // Metodo para listar Empresa
    public List<Empresa> getEmpresaList() {
        return empresaFacade.findAll();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    
    
}
