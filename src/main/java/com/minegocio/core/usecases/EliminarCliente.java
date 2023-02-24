package com.minegocio.core.usecases;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.ports.IPersistenciaCuenta;

public class EliminarCliente {

    public boolean eliminarCuenta(Cliente cliente,
            IPersistenciaCuenta iPersistenciaCuenta) {
        return iPersistenciaCuenta.eliminarCuenta(cliente);
    }

}
