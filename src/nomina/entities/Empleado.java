/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RickyTB
 */
@Entity
@Table(name = "Empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findById", query = "SELECT e FROM Empleado e WHERE e.id = :id")
    , @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleado.findByApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido")
    , @NamedQuery(name = "Empleado.findByCedula", query = "SELECT e FROM Empleado e WHERE e.cedula = :cedula")
    , @NamedQuery(name = "Empleado.findByDireccion", query = "SELECT e FROM Empleado e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empleado.findByEmail", query = "SELECT e FROM Empleado e WHERE e.email = :email")
    , @NamedQuery(name = "Empleado.findByNacionalidad", query = "SELECT e FROM Empleado e WHERE e.nacionalidad = :nacionalidad")
    , @NamedQuery(name = "Empleado.findBySexo", query = "SELECT e FROM Empleado e WHERE e.sexo = :sexo")
    , @NamedQuery(name = "Empleado.findByEstadoCivil", query = "SELECT e FROM Empleado e WHERE e.estadoCivil = :estadoCivil")
    , @NamedQuery(name = "Empleado.findBySueldo", query = "SELECT e FROM Empleado e WHERE e.sueldo = :sueldo")
    , @NamedQuery(name = "Empleado.findByNumeroCuenta", query = "SELECT e FROM Empleado e WHERE e.numeroCuenta = :numeroCuenta")
    , @NamedQuery(name = "Empleado.findByFechaIngreso", query = "SELECT e FROM Empleado e WHERE e.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Empleado.findByFechaSalida", query = "SELECT e FROM Empleado e WHERE e.fechaSalida = :fechaSalida")})
public class Empleado implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

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
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "telefono")
    private int telefono;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Basic(optional = false)
    @Column(name = "sueldo")
    private BigDecimal sueldo;
    @Basic(optional = false)
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Basic(optional = true)
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<Permiso> permisoList;
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cargo cargoId;
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Departamento departamentoId;
    @JoinColumn(name = "tipo_contrato_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoContrato tipoContratoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<CargaFamiliar> cargaFamiliarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<Rol> rolList;

    public Empleado() {
    }

    public Empleado(String nombre, String apellido, String cedula, String direccion, int telefono, String email, String nacionalidad, String sexo, String estadoCivil, BigDecimal sueldo, String numeroCuenta, Date fechaIngreso, Date fechaSalida) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.sueldo = sueldo;
        this.numeroCuenta = numeroCuenta;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        String oldApellido = this.apellido;
        this.apellido = apellido;
        changeSupport.firePropertyChange("apellido", oldApellido, apellido);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        String oldCedula = this.cedula;
        this.cedula = cedula;
        changeSupport.firePropertyChange("cedula", oldCedula, cedula);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        String oldDireccion = this.direccion;
        this.direccion = direccion;
        changeSupport.firePropertyChange("direccion", oldDireccion, direccion);
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        int oldTelefono = this.telefono;
        this.telefono = telefono;
        changeSupport.firePropertyChange("telefono", oldTelefono, telefono);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        String oldNacionalidad = this.nacionalidad;
        this.nacionalidad = nacionalidad;
        changeSupport.firePropertyChange("nacionalidad", oldNacionalidad, nacionalidad);
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        String oldSexo = this.sexo;
        this.sexo = sexo;
        changeSupport.firePropertyChange("sexo", oldSexo, sexo);
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        String oldEstadoCivil = this.estadoCivil;
        this.estadoCivil = estadoCivil;
        changeSupport.firePropertyChange("estadoCivil", oldEstadoCivil, estadoCivil);
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        BigDecimal oldSueldo = this.sueldo;
        this.sueldo = sueldo;
        changeSupport.firePropertyChange("sueldo", oldSueldo, sueldo);
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        String oldNumeroCuenta = this.numeroCuenta;
        this.numeroCuenta = numeroCuenta;
        changeSupport.firePropertyChange("numeroCuenta", oldNumeroCuenta, numeroCuenta);
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        Date oldFechaIngreso = this.fechaIngreso;
        this.fechaIngreso = fechaIngreso;
        changeSupport.firePropertyChange("fechaIngreso", oldFechaIngreso, fechaIngreso);
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        Date oldFechaSalida = this.fechaSalida;
        this.fechaSalida = fechaSalida;
        changeSupport.firePropertyChange("fechaSalida", oldFechaSalida, fechaSalida);
    }

    @XmlTransient
    public List<Permiso> getPermisoList() {
        return permisoList;
    }

    public void setPermisoList(List<Permiso> permisoList) {
        this.permisoList = permisoList;
    }

    public Cargo getCargoId() {
        return cargoId;
    }

    public void setCargoId(Cargo cargoId) {
        Cargo oldCargoId = this.cargoId;
        this.cargoId = cargoId;
        changeSupport.firePropertyChange("cargoId", oldCargoId, cargoId);
    }

    public Departamento getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Departamento departamentoId) {
        Departamento oldDepartamentoId = this.departamentoId;
        this.departamentoId = departamentoId;
        changeSupport.firePropertyChange("departamentoId", oldDepartamentoId, departamentoId);
    }

    public TipoContrato getTipoContratoId() {
        return tipoContratoId;
    }

    public void setTipoContratoId(TipoContrato tipoContratoId) {
        TipoContrato oldTipoContratoId = this.tipoContratoId;
        this.tipoContratoId = tipoContratoId;
        changeSupport.firePropertyChange("tipoContratoId", oldTipoContratoId, tipoContratoId);
    }

    @XmlTransient
    public List<CargaFamiliar> getCargaFamiliarList() {
        return cargaFamiliarList;
    }

    public void setCargaFamiliarList(List<CargaFamiliar> cargaFamiliarList) {
        this.cargaFamiliarList = cargaFamiliarList;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
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
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Empleado[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public Object[] toTableRow() {
        Object[] rowData = {id, nombre, apellido, cedula};
        return rowData;
    }

}
