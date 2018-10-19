
package sv.edu.udb.Controller;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sv.edu.udb.Model.Detalleoferta;
import sv.edu.udb.Model.Facade.VentaFacade;
import sv.edu.udb.Model.Facade.DetalleofertaFacade;
import sv.edu.udb.Model.Venta;

/**
 *
 * @author Manuel
 */
@Named(value = "ventaController")
@RequestScoped
public class VentaController {

    @EJB
    VentaFacade ventaFacade;
    
    @EJB
    DetalleofertaFacade detalleFacade;
    
    private List<Venta> ventaList;
    
    private Venta venta;
    private Detalleoferta detalle;
    private LoginController login = new LoginController();
    
    public VentaController() {
        venta = new Venta();
        detalle = new Detalleoferta();
        venta.setIdDetalle(detalle);
    }
    
     // Metodo para listar Venta
    public List<Venta> getVentaList() {;
        return ventaFacade.findAll();
    }
    
    // Metodo para listar DetalleOferta
    public List<Detalleoferta> getDetalleofertaList(){
        return detalleFacade.findAll();
    }
    
    // Metodo para nueva Venta
    public String nuevaVenta(){
        
        venta.setFechaVenta(new Date());
        venta.setCodCliente(login.getAuthUser().getCodUsuario());
        ventaFacade.create(venta);
        venta = new Venta();
        return "GetVenta?faces-redirect=true";
    }
    
    //Metodo que obtiene los valores de Venta para luego poder modificar
    public String editarVenta(Venta v){
        venta = v;
        return "UpdateVenta";
    }
    
    // Metodo para modificar Venta
    public String modificarVenta(){
        ventaFacade.edit(venta);
        return "GetVenta?faces-redirect=true";
    }
    
    // Metodo para eliminar Venta
    public void eliminarVenta(String codigo){
        String CodVenta = codigo;
        Venta ventaCod = ventaFacade.find(CodVenta);
        ventaFacade.remove(ventaCod);
        
        //Agrega un mensaje
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminada", "Venta Eliminada");
        FacesContext.getCurrentInstance().addMessage(null,message);
    }
    
    // Setter $ Getter
    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
   
}
