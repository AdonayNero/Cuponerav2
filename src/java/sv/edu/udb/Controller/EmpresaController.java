
package sv.edu.udb.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.jboss.weld.logging.Category;
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
    private LoginController login = new LoginController();
               
    public EmpresaController() {
        empresa = new Empresa();
    }
    
    // Metodo para listar Empresa
    public List<Empresa> getEmpresaList() {
        return empresaFacade.findAll();
    }
    
    // Metodo para listar Empresa por Encargado
    public List<Empresa> getEmpresaListByEnc() {
        return empresaFacade.empresaByEncargado(login.getAuthUser().getCodUsuario());
    }
    
    public String create(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        empresa.setCodEmpresa(this.genCodigo());
        empresa.setEncargado(login.getAuthUser().getCodUsuario());
        empresa.setEstado(" ");
        empresa.setPorcentaje("5");
        empresaFacade.create(empresa);
        empresa = new Empresa();
        
        try {
            context.getExternalContext().redirect(request.getContextPath()+"/faces/Sucursal/AddSucursal.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return request.getContextPath()+"/faces/Sucursal/AddSucursal";
    }
    
    public void delete(Empresa e){
        empresaFacade.remove(empresa);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Empresa Eliminada");
        FacesContext.getCurrentInstance().addMessage(null,message);
        
    }
    public String findById(Empresa e){
        empresa = empresaFacade.find(e.getCodEmpresa());
        return "UpdateEmpresa";
    }
    public String update(){
        empresaFacade.edit(empresa);
        empresa = new Empresa();
        return "GetEmpresa";
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public String genCodigo(){
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int aleatorio = rnd.nextInt(9000);
        String codigo = "EM"+aleatorio;
        return codigo;
    }
    
}
