
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sv.edu.udb.Model.Facade.OfertaFacade;
import sv.edu.udb.Model.Oferta;

/**
 *
 * @author Manuel
 */
@Named(value = "ofertaController")
@RequestScoped
public class OfertaController {

    @EJB
    OfertaFacade ofertaFacade;
    
    private List<Oferta> ofertaList;
    
    private Oferta oferta;
    
    public OfertaController() {
        oferta = new Oferta();
    }
    
    // Metodo para listar Empresa
    public List<Oferta> getOfertaList() {
        return ofertaFacade.findAll();
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }
    
    
    
    
}
