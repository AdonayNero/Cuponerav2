
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
    
    // Setter $ Getter
    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
   
}
