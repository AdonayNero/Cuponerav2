
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private LoginController login = new LoginController();
    
    public OfertaController() {
        oferta = new Oferta();
    }
    
    // Metodo para listar ofertas
    public List<Oferta> getOfertaList() {
        return ofertaFacade.findAll();
    }
    
    public List<Oferta> getOfertaByEnc() {
        return ofertaFacade.ofertaByEnc(login.getAuthUser().getCodUsuario());
    }
    
    //Metodo para nueva oferta
    public String nuevaOferta(){
        oferta.setCodOferta(login.getAuthUser().getCodUsuario()+genToken());
        oferta.setEstado("inactivo");
        oferta.setObservaciones("");
        ofertaFacade.create(oferta);
        return "/DetalleOferta/AddDetalleOferta?faces-redirect=true";
    }
    
    //Metodo que obtiene los valores de Oferta para luego poder modificar
    public String editarOferta(Oferta o){
        oferta = o;
        return "UpdateOferta";
    }
    
    //Metodo para modificar Oferta
    public String modificarOferta(){
        ofertaFacade.edit(oferta);
        return "GetOferta?faces-redirect=true";
    }
    
    //Metodo para eliminar oferta
    public void eliminarOferta(String c){
        String codOferta = c;
        Oferta ofertacod = ofertaFacade.find(codOferta);
        ofertaFacade.remove(ofertacod);
       
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminada", "Oferta Eliminada");
        FacesContext.getCurrentInstance().addMessage(null,message);
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }
    
    public String genToken(){
        String token = "";
        int a;
    for (int i = 0; i < 7; i++) {
        if (i < 3) {    // 0,1,2,3 posiciones de numeros
           
            do {
                a = (int) (Math.random() * 26 + 65);///
            } while (a == 65 || a == 69 || a == 73 || a == 79 || a == 85);

            char letra = (char) a;
           
                token = token + letra;
           
        }
    }
    return token;
    }
    
}
