
package sv.edu.udb.Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Manuel
 */
@Entity
@Table(name = "oferta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oferta.findAll", query = "SELECT o FROM Oferta o")
    , @NamedQuery(name = "Oferta.findByCodOferta", query = "SELECT o FROM Oferta o WHERE o.codOferta = :codOferta")
    , @NamedQuery(name = "Oferta.findByTitulo", query = "SELECT o FROM Oferta o WHERE o.titulo = :titulo")
    , @NamedQuery(name = "Oferta.findBySubTitulo", query = "SELECT o FROM Oferta o WHERE o.subTitulo = :subTitulo")
    , @NamedQuery(name = "Oferta.findByImg", query = "SELECT o FROM Oferta o WHERE o.img = :img")
    , @NamedQuery(name = "Oferta.findByValorRegular", query = "SELECT o FROM Oferta o WHERE o.valorRegular = :valorRegular")
    , @NamedQuery(name = "Oferta.findByValorOferta", query = "SELECT o FROM Oferta o WHERE o.valorOferta = :valorOferta")
    , @NamedQuery(name = "Oferta.findByEstado", query = "SELECT o FROM Oferta o WHERE o.estado = :estado")})
public class Oferta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codOferta")
    private String codOferta;
   
    @Basic(optional = false)
    @Column(name = "Titulo")
    private String titulo;
    
    @Basic(optional = false)
    @Column(name = "subTitulo")
    private String subTitulo;
    
    @Basic(optional = false)
    @Column(name = "img")
    private String img;
    
    @Basic(optional = false)
    @Column(name = "detalleGenerales")
    private String detalleGenerales;
    
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @Column(name = "valorRegular")
    private double valorRegular;
    
    @Basic(optional = false)
    @Column(name = "valorOferta")
    private double valorOferta;
    
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    
    @Basic(optional = false)
    @Column(name = "observaciones")
    private String observaciones;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codOferta")
    private Collection<Detalleoferta> detalleofertaCollection;

    public Oferta() {
    }

    public Oferta(String codOferta) {
        this.codOferta = codOferta;
    }

    public Oferta(String codOferta, String titulo, String subTitulo, String img, String detalleGenerales, String descripcion, double valorRegular, double valorOferta, String estado, String observaciones) {
        this.codOferta = codOferta;
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.img = img;
        this.detalleGenerales = detalleGenerales;
        this.descripcion = descripcion;
        this.valorRegular = valorRegular;
        this.valorOferta = valorOferta;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public String getCodOferta() {
        return codOferta;
    }

    public void setCodOferta(String codOferta) {
        this.codOferta = codOferta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDetalleGenerales() {
        return detalleGenerales;
    }

    public void setDetalleGenerales(String detalleGenerales) {
        this.detalleGenerales = detalleGenerales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValorRegular() {
        return valorRegular;
    }

    public void setValorRegular(double valorRegular) {
        this.valorRegular = valorRegular;
    }

    public double getValorOferta() {
        return valorOferta;
    }

    public void setValorOferta(double valorOferta) {
        this.valorOferta = valorOferta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public Collection<Detalleoferta> getDetalleofertaCollection() {
        return detalleofertaCollection;
    }

    public void setDetalleofertaCollection(Collection<Detalleoferta> detalleofertaCollection) {
        this.detalleofertaCollection = detalleofertaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codOferta != null ? codOferta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oferta)) {
            return false;
        }
        Oferta other = (Oferta) object;
        if ((this.codOferta == null && other.codOferta != null) || (this.codOferta != null && !this.codOferta.equals(other.codOferta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.Model.Oferta[ codOferta=" + codOferta + " ]";
    }
    
}
