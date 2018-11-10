
package sv.edu.udb.Controller;

import java.util.ArrayList;
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
    private int cant;
    private int deO;
    
    private Detalleoferta detalle;
    private LoginController login = new LoginController();
    
    public VentaController() {
        venta = new Venta();
        detalle = new Detalleoferta();
        venta.setIdDetalle(detalle);
    }
    
     // Metodo para listar Venta
    public List<Venta> getVentaList() {;
        ventaList = new ArrayList<>();
        ventaList = ventaFacade.ventaByEmp(login.getAuthUser().getCodUsuario());
        return ventaList;
    }
    
    public List<Venta> getVentaListByUsr() {;
        ventaList = null;
        ventaList = new ArrayList<>();
        
        ventaList = ventaFacade.ventaByUsr(login.getAuthUser().getCodUsuario()); 
        return ventaList;
    }
    
    
    
    // Metodo para listar DetalleOferta
    public List<Detalleoferta> getDetalleofertaList(int d){
        return detalleFacade.listbyId(d);
    }
    
    // Metodo para nueva Venta
    public String nuevaVenta(){
            venta.setCodCupon(login.getAuthUser().getCodUsuario()+codeCupon());
            venta.setFechaVenta(new Date());
            venta.setCodCliente(login.getAuthUser().getCodUsuario());
            venta.setFormaPago("cdsv");
            venta.setEstado("activo");
            ventaFacade.create(venta);
            return "/Venta/GetVenta?faces-redirect=true";
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

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getDeO() {
        return deO;
    }

    public void setDeO(int deO) {
        this.deO = deO;
    }
    
    
    public String codeCupon(){
        String token = "";
        int a;
    for (int i = 0; i < 7; i++) {
        if (i < 4) {    // 0,1,2,3 posiciones de numeros
            token = (int) (Math.random() * 5) + "" + token;

        } else {       // 4,5,6 posiciones de letras
            do {
                a = (int) (Math.random() * 26 + 65);///
            } while (a == 65 || a == 69 || a == 73 || a == 79 || a == 85);

            char letra = (char) a;
            if (i == 4) {
                token = token + letra;
            } else {
                token = token + "" + letra;
            }

        }
    }
    return token;
    }
    
    
   
}
