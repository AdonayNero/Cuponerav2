
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private LoginController login = new LoginController();
    
    public SucursalController() {
        sucursal = new Sucursal();
        empresa = new Empresa();
        sucursal.setCodEmpresa(empresa);
    }
    
    // Metodo para listar Sucursal
    public List<Sucursal> getSucursalList() {
        return sucursalFacade.findAll();
    }
    
    public List<Sucursal> getSucursalListByEnc() {
        return sucursalFacade.SucursalByEncargado(login.getAuthUser().getCodUsuario());
        //return sucursalFacade.findAll();
    }
    
    // Metodo para listar Empresa
    public List<Empresa> getEmpresaList(){
        return empresaFacade.findAll();
    }
    
    // Metodo para nueva Sucursal
    public String nuevaSucursal(){
        sucursalFacade.create(getSucursal());
        return "/Oferta/AddOferta?faces-redirect=true";
    }
    
    //Metodo que obtiene los valores de Sucursal para luego poder modificar
    public String editarSucursal(Sucursal s){
        sucursal = s;
        return "UpdateSucursal";
    }
    
    // Metodo para modificar Sucursal
    public String modificarSucursal(){
        sucursalFacade.edit(sucursal);
        return "GetSucursal?faces-redirect=true";
    }
    
    // Metodo para eliminar Sucursal
    public void eliminarSucursal(int id){
        int idSucursal = id;
        Sucursal sucursalid = sucursalFacade.find(idSucursal);
        sucursalFacade.remove(sucursalid);
        
        //Agrega un mensaje
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminada", "Sucursal Eliminada");
        FacesContext.getCurrentInstance().addMessage(null,message);
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
    
    
}
