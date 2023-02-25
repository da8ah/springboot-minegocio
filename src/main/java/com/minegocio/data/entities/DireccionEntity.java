package com.minegocio.data.entities;

import java.io.Serializable;

import com.minegocio.core.entities.Direccion;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Tiber
 */
@Entity
@Table(name = "direcciones")
public class DireccionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddirecciones")
    private Integer iddirecciones;
    @Basic(optional = false)
    @Column(name = "provincia")
    private String provincia;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @ManyToOne
    @JoinColumn(name = "idclientes_fk")
    private ClienteEntity clienteEntity;

    public DireccionEntity() {
    }

    public DireccionEntity(ClienteEntity clienteEntity, Integer iddirecciones) {
        this.clienteEntity = clienteEntity;
        this.iddirecciones = iddirecciones;
    }

    public DireccionEntity(ClienteEntity clienteEntity, Integer iddirecciones, String provincia, String ciudad,
            String direccion) {
        this.clienteEntity = clienteEntity;
        this.iddirecciones = iddirecciones;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public DireccionEntity(ClienteEntity clienteEntity, String provincia, String ciudad, String direccion) {
        this.clienteEntity = clienteEntity;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public Integer getIddirecciones() {
        return iddirecciones;
    }

    public void setIddirecciones(Integer iddirecciones) {
        this.iddirecciones = iddirecciones;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
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
        return "Direcciones[ idclientes=" + clienteEntity.getIdclientes() + "iddirecciones=" + iddirecciones + " ]";
    }

    public Direccion toDireccion() {
        return new Direccion(this.iddirecciones.toString(), this.provincia, this.ciudad, this.direccion);
    }

}
