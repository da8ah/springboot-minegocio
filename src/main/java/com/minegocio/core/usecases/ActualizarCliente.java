package com.minegocio.core.usecases;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.ports.IPersistenciaCuenta;

public class ActualizarCliente {

    public Cliente actualizarCuenta(Cliente cliente,
            IPersistenciaCuenta iPersistenciaCuenta) {
        return iPersistenciaCuenta.actualizarCuenta(cliente);
    }

}
