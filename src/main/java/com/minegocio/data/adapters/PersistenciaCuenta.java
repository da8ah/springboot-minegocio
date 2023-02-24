package com.minegocio.data.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.entities.Direccion;
import com.minegocio.core.ports.IPersistenciaCuenta;
import com.minegocio.data.entities.ClienteEntity;

@Service
public class PersistenciaCuenta implements IPersistenciaCuenta {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente filtrarCuentas(String query) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filtrarCuentas'");
    }

    @Override
    public boolean guardarCuentaCreada(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        return this.clienteRepository.save(clienteEntity).getIdclientes() != null;
    }

    @Override
    public Cliente actualizarCuenta(Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarCuenta'");
    }

    @Override
    public boolean eliminarCuenta(Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCuenta'");
    }

    @Override
    public List<Direccion> obtenerDirecciones(Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerDirecciones'");
    }

}
