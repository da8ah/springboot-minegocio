package com.minegocio.data.entities;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.NamedQueries;
// import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author Tiber
 */
@Entity
@Table(name = "direcciones")
// @NamedQueries({
// @NamedQuery(name = "Direcciones.findAll", query = "SELECT d FROM Direcciones
// d"),
// @NamedQuery(name = "Direcciones.findByIddirecciones", query = "SELECT d FROM
// Clientes c, Direcciones d WHERE c.idclientes = :idclientes AND
// d.iddirecciones = :iddirecciones")
// })
public class DireccionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddirecciones")
    private Integer iddirecciones;
    @Basic(optional = false)
    @Column(name = "provincia")
    private Integer provincia;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private Integer ciudad;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;

    public DireccionEntity() {
    }

    public DireccionEntity(Integer iddirecciones) {
        this.iddirecciones = iddirecciones;
    }

    public DireccionEntity(Integer iddirecciones, Integer provincia, Integer ciudad, String direccion) {
        this.iddirecciones = iddirecciones;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public Integer getIddirecciones() {
        return iddirecciones;
    }

    public void setIddirecciones(Integer iddirecciones) {
        this.iddirecciones = iddirecciones;
    }

    public Integer getProvincia() {
        return provincia;
    }

    public void setProvincia(Integer provincia) {
        this.provincia = provincia;
    }

    public Integer getCiudad() {
        return ciudad;
    }

    public void setCiudad(Integer ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddirecciones != null ? iddirecciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionEntity)) {
            return false;
        }
        DireccionEntity other = (DireccionEntity) object;
        if ((this.iddirecciones == null && other.iddirecciones != null)
                || (this.iddirecciones != null && !this.iddirecciones.equals(other.iddirecciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Direcciones[ iddirecciones=" + iddirecciones + " ]";
    }

}
