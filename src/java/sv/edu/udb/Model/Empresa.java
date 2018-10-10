
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
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByCodEmpresa", query = "SELECT e FROM Empresa e WHERE e.codEmpresa = :codEmpresa")
    , @NamedQuery(name = "Empresa.findByNombre", query = "SELECT e FROM Empresa e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empresa.findByNitEmp", query = "SELECT e FROM Empresa e WHERE e.nitEmp = :nitEmp")
    , @NamedQuery(name = "Empresa.findByEncargado", query = "SELECT e FROM Empresa e WHERE e.encargado = :encargado")
    , @NamedQuery(name = "Empresa.findByPorcentaje", query = "SELECT e FROM Empresa e WHERE e.porcentaje = :porcentaje")
    , @NamedQuery(name = "Empresa.findByEstado", query = "SELECT e FROM Empresa e WHERE e.estado = :estado")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codEmpresa")
    private String codEmpresa;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "nitEmp")
    private String nitEmp;

    @Basic(optional = false)
    @Column(name = "encargado")
    private String encargado;

    @Basic(optional = false)
    @Column(name = "porcentaje")
    private String porcentaje;

    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;

    @Basic(optional = false)
    @Column(name = "observacion")
    private String observacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEmpresa")
    private Collection<Sucursal> sucursalCollection;

    public Empresa() {
    }

    public Empresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public Empresa(String codEmpresa, String nombre, String nitEmp, String encargado, String porcentaje, String estado, String observacion) {
        this.codEmpresa = codEmpresa;
        this.nombre = nombre;
        this.nitEmp = nitEmp;
        this.encargado = encargado;
        this.porcentaje = porcentaje;
        this.estado = estado;
        this.observacion = observacion;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNitEmp() {
        return nitEmp;
    }

    public void setNitEmp(String nitEmp) {
        this.nitEmp = nitEmp;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @XmlTransient
    public Collection<Sucursal> getSucursalCollection() {
        return sucursalCollection;
    }

    public void setSucursalCollection(Collection<Sucursal> sucursalCollection) {
        this.sucursalCollection = sucursalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEmpresa != null ? codEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.codEmpresa == null && other.codEmpresa != null) || (this.codEmpresa != null && !this.codEmpresa.equals(other.codEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.Model.Empresa[ codEmpresa=" + codEmpresa + " ]";
    }
    
}
