package com.minegocio.data.entities;

import java.io.Serializable;
import java.util.List;

import com.minegocio.core.entities.Direccion;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.NamedQueries;
// import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author Tiber
 */
@Entity
@Table(name = "clientes")
// @NamedQueries({
// @NamedQuery(name = "Clientes.findByIdclientes", query = "SELECT c FROM
// Clientes c WHERE c.idclientes = :idclientes"),
// })
public class ClienteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclientes")
    private Integer idclientes;
    @Basic(optional = false)
    @Column(name = "tipoIdentificacion")
    private Integer tipoIdentificacion;
    @Basic(optional = false)
    @Column(name = "numIdentificacion")
    private Integer numIdentificacion;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "movil")
    private String movil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientesIdclientes")
    private List<Direccion> direcciones;

    public ClienteEntity() {
    }

    public ClienteEntity(Integer idclientes) {
        this.idclientes = idclientes;
    }

    public ClienteEntity(Integer idclientes, Integer tipoIdentificacion, Integer numIdentificacion, String nombres,
            String direccion, String correo, String movil) {
        this.idclientes = idclientes;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numIdentificacion = numIdentificacion;
        this.nombres = nombres;
        this.direccion = direccion;
        this.correo = correo;
        this.movil = movil;
    }

    public Integer getIdclientes() {
        return idclientes;
    }

    public void setIdclientes(Integer idclientes) {
        this.idclientes = idclientes;
    }

    public Integer getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Integer tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Integer getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(Integer numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclientes != null ? idclientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteEntity)) {
            return false;
        }
        ClienteEntity other = (ClienteEntity) object;
        if ((this.idclientes == null && other.idclientes != null)
                || (this.idclientes != null && !this.idclientes.equals(other.idclientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clientes[ idclientes=" + idclientes + " ]";
    }

}
