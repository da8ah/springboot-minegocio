package com.minegocio.data.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.entities.Direccion;
import com.minegocio.core.ports.IPersistenciaCuenta;
import com.minegocio.data.entities.ClienteEntity;
import com.minegocio.data.entities.DireccionEntity;

@Service
public class PersistenciaCuenta implements IPersistenciaCuenta {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ArrayList<Cliente> filtrarCuentas(String query) {
        ArrayList<ClienteEntity> entities = (ArrayList<ClienteEntity>) this.clienteRepository
                .findByNumIdentificacion(query);
        ArrayList<Cliente> clientes = entities.stream()
                .map(element -> element.toCliente())
                .collect(Collectors.toCollection(ArrayList::new));
        return clientes;
    }

    @Override
    public boolean guardarCuentaCreada(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNombres(cliente.getNombres());
        clienteEntity.setTipoIdentificacion(cliente.getTipoIdentificacion());
        clienteEntity.setNumIdentificacion(cliente.getNumIdentificacion());
        clienteEntity.setCorreo(cliente.getCorreo());
        clienteEntity.setMovil(cliente.getMovil());
        ArrayList<DireccionEntity> direcciones = new ArrayList<>();
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setProvincia(cliente.getDirecciones().get(0).getProvincia());
        direccionEntity.setCiudad(cliente.getDirecciones().get(0).getCiudad());
        direccionEntity.setDireccion(cliente.getDirecciones().get(0).getDireccion());
        direcciones.add(direccionEntity);
        clienteEntity.setDirecciones(direcciones);

        return this.clienteRepository.save(clienteEntity).getIdclientes() != null;
    }

    @Override
    public Cliente actualizarCuenta(Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarCuenta'");
    }

    @Override
    public boolean eliminarCuenta(Cliente cliente) {
        // this.clienteRepository.de
        return false;
    }

    @Override
    public List<Direccion> obtenerDirecciones(Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerDirecciones'");
    }

}
