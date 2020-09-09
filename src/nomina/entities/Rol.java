/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RickyTB
 */
@Entity
@Table(name = "Rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
    , @NamedQuery(name = "Rol.findById", query = "SELECT r FROM Rol r WHERE r.id = :id")
    , @NamedQuery(name = "Rol.findByAnio", query = "SELECT r FROM Rol r WHERE r.anio = :anio")
    , @NamedQuery(name = "Rol.findByMes", query = "SELECT r FROM Rol r WHERE r.mes = :mes")
    , @NamedQuery(name = "Rol.findByHorasTrabajadas", query = "SELECT r FROM Rol r WHERE r.horasTrabajadas = :horasTrabajadas")
    , @NamedQuery(name = "Rol.findByHorasTrabajadasCincuenta", query = "SELECT r FROM Rol r WHERE r.horasTrabajadasCincuenta = :horasTrabajadasCincuenta")
    , @NamedQuery(name = "Rol.findByHorasTrabajadasCien", query = "SELECT r FROM Rol r WHERE r.horasTrabajadasCien = :horasTrabajadasCien")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "anio")
    private int anio;
    @Basic(optional = false)
    @Column(name = "mes")
    private int mes;
    @Basic(optional = false)
    @Column(name = "horas_trabajadas")
    private int horasTrabajadas;
    @Basic(optional = false)
    @Column(name = "horas_trabajadas_cincuenta")
    private int horasTrabajadasCincuenta;
    @Basic(optional = false)
    @Column(name = "horas_trabajadas_cien")
    private int horasTrabajadasCien;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolId")
    private List<Concepto> conceptoList;
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleado empleadoId;

    public Rol() {
    }

    public Rol(int anio, int mes, int horasTrabajadas, int horasTrabajadasCincuenta, int horasTrabajadasCien) {
        this.anio = anio;
        this.mes = mes;
        this.horasTrabajadas = horasTrabajadas;
        this.horasTrabajadasCincuenta = horasTrabajadasCincuenta;
        this.horasTrabajadasCien = horasTrabajadasCien;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public int getHorasTrabajadasCincuenta() {
        return horasTrabajadasCincuenta;
    }

    public void setHorasTrabajadasCincuenta(int horasTrabajadasCincuenta) {
        this.horasTrabajadasCincuenta = horasTrabajadasCincuenta;
    }

    public int getHorasTrabajadasCien() {
        return horasTrabajadasCien;
    }

    public void setHorasTrabajadasCien(int horasTrabajadasCien) {
        this.horasTrabajadasCien = horasTrabajadasCien;
    }

    @XmlTransient
    public List<Concepto> getConceptoList() {
        return conceptoList;
    }

    public void setConceptoList(List<Concepto> conceptoList) {
        this.conceptoList = conceptoList;
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
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rol[ id=" + id + " ]";
    }

    public Object[] toTableRow() {
        Object[] rowData = {id, anio, mes, horasTrabajadas, horasTrabajadasCien, horasTrabajadasCincuenta, 100.12};
        return rowData;
    }

}
