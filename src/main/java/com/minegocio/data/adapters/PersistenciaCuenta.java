package com.minegocio.data.adapters;

import java.util.ArrayList;
import java.util.regex.Pattern;
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

    @Autowired
    public PersistenciaCuenta(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ArrayList<Cliente> filtrarCuentas(String query) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        Pattern patternQuery;
        patternQuery = Pattern.compile("^[0-9]+$");
        if (patternQuery.matcher(query).matches()) {
            ArrayList<ClienteEntity> entities = (ArrayList<ClienteEntity>) this.clienteRepository
                    .findByNumIdentificacion(query);
            clientes = entities.stream()
                    .map(element -> element.toClienteConUnaDireccion())
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        patternQuery = Pattern.compile("^[A-Za-zÁáÉéÍíÓóÚúÜüÑñ\\s]+$", Pattern.CASE_INSENSITIVE);
        if (patternQuery.matcher(query).matches()) {
            ArrayList<ClienteEntity> entities = (ArrayList<ClienteEntity>) this.clienteRepository
                    .findByNombres(query);
            clientes = entities.stream()
                    .map(element -> element.toClienteConUnaDireccion())
                    .collect(Collectors.toCollection(ArrayList::new));
        }
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
        direccionEntity.setClienteEntity(clienteEntity);
        direccionEntity.setProvincia(cliente.getDirecciones().get(0).getProvincia());
        direccionEntity.setCiudad(cliente.getDirecciones().get(0).getCiudad());
        direccionEntity.setDireccion(cliente.getDirecciones().get(0).getDireccion());
        direcciones.add(direccionEntity);
        clienteEntity.setDirecciones(direcciones);

        return this.clienteRepository.save(clienteEntity).getIdclientes() != null;
    }

    @Override
    public Cliente actualizarCuenta(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setIdclientes(Integer.valueOf(cliente.getId()));
        clienteEntity.setNombres(cliente.getNombres());
        clienteEntity.setTipoIdentificacion(cliente.getTipoIdentificacion());
        clienteEntity.setNumIdentificacion(cliente.getNumIdentificacion());
        clienteEntity.setCorreo(cliente.getCorreo());
        clienteEntity.setMovil(cliente.getMovil());
        ArrayList<DireccionEntity> direcciones = new ArrayList<>();
        direcciones = cliente.getDirecciones().stream()
                .map((Direccion element) -> {
                    return element.getId().isBlank()
                            ? new DireccionEntity(
                                    clienteEntity,
                                    element.getProvincia(),
                                    element.getCiudad(),
                                    element.getDireccion())
                            : new DireccionEntity(
                                    clienteEntity,
                                    Integer.valueOf(element.getId()),
                                    element.getProvincia(),
                                    element.getCiudad(),
                                    element.getDireccion());
                })
                .collect(Collectors.toCollection(ArrayList::new));
        clienteEntity.setDirecciones(direcciones);

        return this.clienteRepository.save(clienteEntity).toCliente();
    }

    @Override
    public boolean eliminarCuenta(Cliente cliente) {
        try {
            this.clienteRepository.deleteById(Long.parseLong(cliente.getId()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Cliente registrarNuevaDireccion(Cliente cliente, Direccion direccion) {
        cliente = this.clienteRepository.findById(Long.parseLong(cliente.getId())).get().toCliente();
        cliente.getDirecciones().add(direccion);
        return this.actualizarCuenta(cliente);
    }

    @Override
    public Cliente obtenerDirecciones(Cliente cliente) {
        return this.clienteRepository.findById(Long.parseLong(cliente.getId())).get().toCliente();
    }

}
