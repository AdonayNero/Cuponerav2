
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sv.edu.udb.Model.Categoria;
import sv.edu.udb.Model.Detalleoferta;
import sv.edu.udb.Model.Facade.CategoriaFacade;
import sv.edu.udb.Model.Facade.DetalleofertaFacade;
import sv.edu.udb.Model.Facade.OfertaFacade;
import sv.edu.udb.Model.Facade.SucursalFacade;
import sv.edu.udb.Model.Oferta;
import sv.edu.udb.Model.Sucursal;

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
    
    private List<Detalleoferta> detalleList;
    
    private Detalleoferta detalleOferta;
    
    private Sucursal sucursal;
    
    private Oferta oferta;
    
    private Categoria categoria;
    
    public DetalleOfertaController() {
        detalleOferta = new Detalleoferta();
        sucursal = new Sucursal();
        oferta = new Oferta();
        categoria = new Categoria();
        detalleOferta.setIdSucusal(sucursal);
        detalleOferta.setCodOferta(oferta);
        detalleOferta.setIdCategoria(categoria);   
    }
    
    // Metodo para listar DetalleOferta
    public List<Detalleoferta> getDetalleofertaList() {
        return detalleFacade.findAll();
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
    
    // Setter & Getter
    public Detalleoferta getDetalleOferta() {
        return detalleOferta;
    }

    public void setDetalleOferta(Detalleoferta detalleOferta) {
        this.detalleOferta = detalleOferta;
    }
    

    
}
