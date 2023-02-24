package com.minegocio.core.usecases;

import java.util.List;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.ports.IPersistenciaCuenta;

public class BuscarClientes {

    public List<Cliente> medianteString(String query,
            IPersistenciaCuenta iPersistenciaCuenta) {
        return iPersistenciaCuenta.filtrarCuentas(query);
    }

}
