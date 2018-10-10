
package sv.edu.udb.Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manuel
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
    , @NamedQuery(name = "Venta.findByCodCupon", query = "SELECT v FROM Venta v WHERE v.codCupon = :codCupon")
    , @NamedQuery(name = "Venta.findByCodCliente", query = "SELECT v FROM Venta v WHERE v.codCliente = :codCliente")
    , @NamedQuery(name = "Venta.findByFechaVenta", query = "SELECT v FROM Venta v WHERE v.fechaVenta = :fechaVenta")
    , @NamedQuery(name = "Venta.findByFormaPago", query = "SELECT v FROM Venta v WHERE v.formaPago = :formaPago")
    , @NamedQuery(name = "Venta.findByEstado", query = "SELECT v FROM Venta v WHERE v.estado = :estado")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codCupon")
    private String codCupon;
    
    @Basic(optional = false)
    @Column(name = "codCliente")
    private String codCliente;
    
    @Basic(optional = false)
    @Column(name = "fechaVenta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    
    @Basic(optional = false)
    @Column(name = "formaPago")
    private String formaPago;
    
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    
    @JoinColumn(name = "idDetalle", referencedColumnName = "idDetalle")
    @ManyToOne(optional = false)
    private Detalleoferta idDetalle;

    public Venta() {
    }

    public Venta(String codCupon) {
        this.codCupon = codCupon;
    }

    public Venta(String codCupon, String codCliente, Date fechaVenta, String formaPago, String estado) {
        this.codCupon = codCupon;
        this.codCliente = codCliente;
        this.fechaVenta = fechaVenta;
        this.formaPago = formaPago;
        this.estado = estado;
    }

    public String getCodCupon() {
        return codCupon;
    }

    public void setCodCupon(String codCupon) {
        this.codCupon = codCupon;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Detalleoferta getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Detalleoferta idDetalle) {
        this.idDetalle = idDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCupon != null ? codCupon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.codCupon == null && other.codCupon != null) || (this.codCupon != null && !this.codCupon.equals(other.codCupon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.Model.Venta[ codCupon=" + codCupon + " ]";
    }
    
}
