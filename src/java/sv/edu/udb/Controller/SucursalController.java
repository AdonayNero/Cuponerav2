
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sv.edu.udb.Model.Empresa;
import sv.edu.udb.Model.Facade.EmpresaFacade;
import sv.edu.udb.Model.Facade.SucursalFacade;
import sv.edu.udb.Model.Sucursal;

/**
 *
 * @author Manuel
 */
@Named(value = "sucursalController")
@RequestScoped
public class SucursalController {

    @EJB
    SucursalFacade sucursalFacade;
    
    @EJB
    EmpresaFacade empresaFacade;
    
    private List<Sucursal> sucursalList;
    
    private Sucursal sucursal;
    private Empresa empresa;
    
    public SucursalController() {
        sucursal = new Sucursal();
        empresa = new Empresa();
        sucursal.setCodEmpresa(empresa);
    }
    
    // Metodo para listar Sucursal
    public List<Sucursal> getSucursalList() {;
        return sucursalFacade.findAll();
    }
    
    // Metodo para listar Empresa
    public List<Empresa> getEmpresaList(){
        return empresaFacade.findAll();
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
    
    
}
