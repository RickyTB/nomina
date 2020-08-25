/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RickyTB
 */
@Entity
@Table(name = "CargaFamiliar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargaFamiliar.findAll", query = "SELECT c FROM CargaFamiliar c")
    , @NamedQuery(name = "CargaFamiliar.findById", query = "SELECT c FROM CargaFamiliar c WHERE c.id = :id")
    , @NamedQuery(name = "CargaFamiliar.findByNombre", query = "SELECT c FROM CargaFamiliar c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CargaFamiliar.findBySexo", query = "SELECT c FROM CargaFamiliar c WHERE c.sexo = :sexo")
    , @NamedQuery(name = "CargaFamiliar.findByFechaNacimiento", query = "SELECT c FROM CargaFamiliar c WHERE c.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "CargaFamiliar.findByParentezco", query = "SELECT c FROM CargaFamiliar c WHERE c.parentezco = :parentezco")})
public class CargaFamiliar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "parentezco")
    private String parentezco;
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleado empleadoId;

    public CargaFamiliar() {
    }

    public CargaFamiliar(String nombre, String sexo, Date fechaNacimiento, String parentezco) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.parentezco = parentezco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }

    public Empleado getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleado empleadoId) {
        this.empleadoId = empleadoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargaFamiliar)) {
            return false;
        }
        CargaFamiliar other = (CargaFamiliar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CargaFamiliar[ id=" + id + " ]";
    }
    
}
