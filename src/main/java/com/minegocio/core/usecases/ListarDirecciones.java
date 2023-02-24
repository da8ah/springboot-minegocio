package com.minegocio.core.usecases;

import java.util.List;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.entities.Direccion;
import com.minegocio.core.ports.IPersistenciaCuenta;

public class ListarDirecciones {

    public Cliente obtenerDireccionesDelCliente(Cliente cliente,
            IPersistenciaCuenta iPersistenciaCuenta) {
        List<Direccion> direcciones = iPersistenciaCuenta.obtenerDirecciones(cliente);
        if (direcciones != null)
            cliente.setDirecciones(direcciones);
        return direcciones != null ? cliente : null;
    }

}
