
package sv.edu.udb.Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Manuel
 */
@Entity
@Table(name = "detalleoferta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleoferta.findAll", query = "SELECT d FROM Detalleoferta d")
    , @NamedQuery(name = "Detalleoferta.findByIdDetalle", query = "SELECT d FROM Detalleoferta d WHERE d.idDetalle = :idDetalle")
    , @NamedQuery(name = "Detalleoferta.findByCantidad", query = "SELECT d FROM Detalleoferta d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "Detalleoferta.findByFechaInicio", query = "SELECT d FROM Detalleoferta d WHERE d.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Detalleoferta.findByFechaFin", query = "SELECT d FROM Detalleoferta d WHERE d.fechaFin = :fechaFin")
    , @NamedQuery(name = "Detalleoferta.findByEstado", query = "SELECT d FROM Detalleoferta d WHERE d.estado = :estado")})
public class Detalleoferta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDetalle")
    private Integer idDetalle;

    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;

    @Basic(optional = false)
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Basic(optional = false)
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalle")
    private Collection<Venta> ventaCollection;

    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")
    @ManyToOne(optional = false)
    private Categoria idCategoria;

    @JoinColumn(name = "codOferta", referencedColumnName = "codOferta")
    @ManyToOne(optional = false)
    private Oferta codOferta;

    @JoinColumn(name = "idSucusal", referencedColumnName = "idSucursal")
    @ManyToOne(optional = false)
    private Sucursal idSucusal;

    public Detalleoferta() {
    }

    public Detalleoferta(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Detalleoferta(Integer idDetalle, int cantidad, Date fechaInicio, Date fechaFin, String estado) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Venta> getVentaCollection() {
        return ventaCollection;
    }

    public void setVentaCollection(Collection<Venta> ventaCollection) {
        this.ventaCollection = ventaCollection;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Oferta getCodOferta() {
        return codOferta;
    }

    public void setCodOferta(Oferta codOferta) {
        this.codOferta = codOferta;
    }

    public Sucursal getIdSucusal() {
        return idSucusal;
    }

    public void setIdSucusal(Sucursal idSucusal) {
        this.idSucusal = idSucusal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleoferta)) {
            return false;
        }
        Detalleoferta other = (Detalleoferta) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.Model.Detalleoferta[ idDetalle=" + idDetalle + " ]";
    }
    
}
