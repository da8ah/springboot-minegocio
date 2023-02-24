package com.minegocio.core.usecases;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.ports.IPersistenciaCuenta;

public class CrearCliente {

    public boolean guardarCuentaCreada(Cliente cliente,
            IPersistenciaCuenta iPersistenciaCuenta) {
        return iPersistenciaCuenta.guardarCuentaCreada(cliente);
    }

}
