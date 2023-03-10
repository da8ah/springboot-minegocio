package com.minegocio.core.usecases;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.entities.Direccion;
import com.minegocio.core.ports.IPersistenciaCuenta;

public class CrearDireccion {

    public Cliente registrarNuevaDireccion(Cliente cliente, Direccion direccion,
            IPersistenciaCuenta iPersistenciaCuenta) {
        return iPersistenciaCuenta.registrarNuevaDireccion(cliente, direccion);
    }

}
