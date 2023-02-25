package com.minegocio.core.ports;

import java.util.List;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.entities.Direccion;

public interface IPersistenciaCuenta {

    public List<Cliente> filtrarCuentas(String query);

    public boolean guardarCuentaCreada(Cliente cliente);

    public Cliente actualizarCuenta(Cliente cliente);

    public boolean eliminarCuenta(Cliente cliente);

    public Cliente registrarNuevaDireccion(Cliente cliente, Direccion direccion);

    public List<Direccion> obtenerDirecciones(Cliente cliente);

}
