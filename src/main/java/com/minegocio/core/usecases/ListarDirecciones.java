package com.minegocio.core.usecases;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.ports.IPersistenciaCuenta;

public class ListarDirecciones {

    public Cliente obtenerDireccionesDelCliente(Cliente cliente,
            IPersistenciaCuenta iPersistenciaCuenta) {
        return iPersistenciaCuenta.obtenerDirecciones(cliente);
    }

}
