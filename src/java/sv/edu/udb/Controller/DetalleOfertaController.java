
package sv.edu.udb.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sv.edu.udb.Model.Categoria;
import sv.edu.udb.Model.Detalleoferta;
import sv.edu.udb.Model.Facade.CategoriaFacade;
import sv.edu.udb.Model.Facade.DetalleofertaFacade;
import sv.edu.udb.Model.Facade.OfertaFacade;
import sv.edu.udb.Model.Facade.SucursalFacade;
import sv.edu.udb.Model.Facade.VentaFacade;
import sv.edu.udb.Model.Oferta;
import sv.edu.udb.Model.Sucursal;
import sv.edu.udb.Model.Venta;

/**
 *
 * @author Manuel
 */
@Named(value = "detalleOfertaController")
@RequestScoped
public class DetalleOfertaController {

    @EJB
    DetalleofertaFacade detalleFacade;
    
    @EJB
    SucursalFacade sucursalFacade;
    
    @EJB
    OfertaFacade ofertaFacade;
    
    @EJB
    CategoriaFacade categoriaFacade;
    
    @EJB
    VentaFacade ventaFacade;
    
    private List<Detalleoferta> detalleList;
    private Detalleoferta detalleOferta;
    private Sucursal sucursal;
    private Oferta oferta;
    private Categoria categoria;
    private Venta venta;
    private LoginController login = new LoginController();
    
    private int cant;
    
    public DetalleOfertaController() {
        cant = 1;
        detalleOferta = new Detalleoferta();
        sucursal = new Sucursal();
        oferta = new Oferta();
        categoria = new Categoria();
        detalleOferta.setIdSucusal(sucursal);
        detalleOferta.setCodOferta(oferta);
        detalleOferta.setIdCategoria(categoria);
        venta = new Venta();
        venta.setIdDetalle(detalleOferta);
        
    }
    
    // Metodo para listar DetalleOferta
    public List<Detalleoferta> getDetalleofertaList() {
        detalleFacade.cambiarEstado();
        return detalleFacade.listbyEstado();
    }
    
    // Metodo para listar Sucursal
    public List<Sucursal> getSucursalList() {
        return sucursalFacade.findAll();
    }
    
    // Metodo para listar Ofertas
    public List<Oferta> getOfertaList() {
        return ofertaFacade.findAll();
    }
    
    // Metodo para listar Categorias
    public List<Categoria> getCategoriaList() {
        return categoriaFacade.findAll();
    }
    
    // Metodo para nuevo DetalleOferta
    public String nuevoDetalleOferta(){
        detalleOferta.setVenta(detalleOferta.getCantidad());
        detalleFacade.create(getDetalleOferta());
        return "GetDetalleOferta?faces-redirect=true";
    }
    
    //Metodo que obtiene los valores de DetalleOferta para luego poder modificar
    public String editarDetalleOferta(Detalleoferta d){
        detalleOferta = d;
        return "UpdateDetalleOferta";
    }
    
    public List<Detalleoferta> getDetallesOfertas(Detalleoferta d){
        
        List<Detalleoferta> lista =new ArrayList<>();
        lista.add(detalleFacade.find(d.getIdDetalle()));
        return lista;
    }
    
    public String buscarOferta(Detalleoferta d){
        detalleOferta = detalleFacade.find(d.getIdDetalle());
        
        return "/Oferta/AcceptOferta";
    }
    
    // Metodo para modificar Venta
    public String modificarDetalleOferta(){
        detalleFacade.edit(detalleOferta);
        return "GetDetalleOferta?faces-redirect=true";
    }
    
    // Metodo para eliminar DetalleOferta
    public void eliminarDetalleOferta(int id){
        int idDetalle = id;
        Detalleoferta detalleId = detalleFacade.find(idDetalle);
        detalleFacade.remove(detalleId);
        
        //Agrega un mensaje
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "DetalleOferta Eliminada");
        FacesContext.getCurrentInstance().addMessage(null,message);
    }
    
    public String nuevaVenta(){
            for (int i = 0; i < cant; i++) {
                detalleOferta = detalleFacade.find(detalleOferta.getIdDetalle());                
                venta.setIdDetalle(detalleOferta);
                venta.setCodCupon(detalleOferta.getIdSucusal().getCodEmpresa().getEncargado()+codeCupon());
                venta.setFechaVenta(new Date());
                venta.setCodCliente(login.getAuthUser().getCodUsuario());
                venta.setFormaPago("credito");
                venta.setEstado("Activo");
                
                ventaFacade.create(venta);                
                detalleOferta.setVenta(detalleOferta.getVenta()-1);
                System.out.println(detalleOferta.getVenta());
                detalleFacade.vendidos( detalleOferta.getVenta(),detalleOferta.getIdDetalle());                
                if (detalleOferta.getVenta()==0) {
                    return "../Index.html";
                }
             }
            return "../Index.html";
    }
    
    // Setter & Getter
    public Detalleoferta getDetalleOferta() {
        return detalleOferta;
    }

    public void setDetalleOferta(Detalleoferta detalleOferta) {
        this.detalleOferta = detalleOferta;
    }

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
