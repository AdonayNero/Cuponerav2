
package sv.edu.udb.Controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sv.edu.udb.Model.Categoria;
import sv.edu.udb.Model.Facade.CategoriaFacade;

/**
 *
 * @author Manuel
 */
@Named(value = "categoriaController")
@RequestScoped
public class CategoriaController {

    @EJB
    CategoriaFacade categoriaFacade;
   
    private List<Categoria> categoriaList;
    
    private Categoria categoria;
    
    public CategoriaController() {
        categoria = new Categoria();
    }
    
    // Metodo para listar Categorias
    public List<Categoria> getCategoriaList() {
        return categoriaFacade.findAll();
    }
    
    // Metodo para nueva Categoria
    public String nuevaCategoria(){
        categoriaFacade.create(getCategoria());
        return "GetCategoria?faces-redirect=true";
    }
    
    //Metodo que obtiene los valores de Categoria para luego poder modificar
    public String editarCategoria(Categoria c){
        categoria = c;
        return "UpdateCategoria";
    }
    
    // Metodo para modificar Categoria
    public String modificarCategoria(){
        categoriaFacade.edit(categoria);
        return "GetCategoria?faces-redirect=true";
    }
    
    // Metodo para eliminar Categoria
    public void eliminarCategoria(int id){
        int idCategoria = id;
        Categoria categoriaid = categoriaFacade.find(idCategoria);
        categoriaFacade.remove(categoriaid);
        
        //Agrega un mensaje
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminada", "Categoría Eliminada");
        FacesContext.getCurrentInstance().addMessage(null,message);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
