package com.minegocio.data.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.entities.Direccion;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author Tiber
 */
@Entity
@Table(name = "clientes")
@NamedQueries({
        @NamedQuery(name = "Clientes.findByNumIdentificacion", query = "SELECT c FROM ClienteEntity c WHERE LOWER(c.numIdentificacion) LIKE '%' || LOWER(:query) || '%'"),
        @NamedQuery(name = "Clientes.findByNombres", query = "SELECT c FROM ClienteEntity c WHERE LOWER(c.nombres) LIKE '%' || LOWER(:query) || '%'")
})
public class ClienteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclientes")
    private Integer idclientes;
    @Basic(optional = false)
    @Column(name = "tipoIdentificacion")
    private String tipoIdentificacion;
    @Basic(optional = false)
    @Column(name = "numIdentificacion")
    private String numIdentificacion;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "movil")
    private String movil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddirecciones")
    private List<DireccionEntity> direcciones;

    public ClienteEntity() {
    }

    public ClienteEntity(Integer idclientes) {
        this.idclientes = idclientes;
    }

    public ClienteEntity(Integer idclientes, String tipoIdentificacion, String numIdentificacion, String nombres,
            String direccion, String correo, String movil) {
        this.idclientes = idclientes;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numIdentificacion = numIdentificacion;
        this.nombres = nombres;
        this.correo = correo;
        this.movil = movil;
    }

    public Integer getIdclientes() {
        return idclientes;
    }

    public void setIdclientes(Integer idclientes) {
        this.idclientes = idclientes;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public List<DireccionEntity> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionEntity> direcciones) {
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

    public Cliente toCliente() {
        Cliente cliente = new Cliente(this.idclientes.toString(),
                this.tipoIdentificacion.equalsIgnoreCase("DNI") ? 0 : 1,
                this.numIdentificacion,
                this.nombres, this.correo, this.movil);

        if (this.direcciones != null) {
            ArrayList<Direccion> direcciones = this.direcciones.stream()
                    .map(element -> element.toDireccion())
                    .collect(Collectors.toCollection(ArrayList::new));
            cliente.setDirecciones(direcciones);
        }
        return cliente;
    }

}
