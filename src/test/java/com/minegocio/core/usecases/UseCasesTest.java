package com.minegocio.core.usecases;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.minegocio.data.adapters.ClienteRepository;
import com.minegocio.data.adapters.PersistenciaCuenta;
import com.minegocio.data.entities.ClienteEntity;
import com.minegocio.data.entities.DireccionEntity;

public class UseCasesTest {

    ClienteRepository clienteRepository = Mockito.mock(ClienteRepository.class);
    PersistenciaCuenta persistenciaCuenta = new PersistenciaCuenta(clienteRepository);

    ClienteEntity clienteEntity = new ClienteEntity(
            1,
            "DNI",
            "1000000001",
            "Annie Andrade",
            "annie.andrade@email.com",
            "+593900000001");
    DireccionEntity direccion = new DireccionEntity(clienteEntity, 1, "Loja", "Loja", "Principal y Secundaria");
    ArrayList<DireccionEntity> direcciones = new ArrayList<>();

    @BeforeEach
    void setUp() {
        direcciones.add(direccion);
        clienteEntity.setDirecciones(direcciones);
    }

    @Test
    void testGuardarCuentaCreada() {
        Mockito.when(clienteRepository.save(any())).thenReturn(clienteEntity);

        CrearCliente usecase = new CrearCliente();
        Assertions.assertEquals(true,
                usecase.guardarCuentaCreada(
                        clienteEntity.toClienteConUnaDireccion(),
                        persistenciaCuenta));
    }

    @Test
    void testFiltrarCuentas() {
        String query = "10";

        ArrayList<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(clienteEntity);
        Mockito.when(clienteRepository.findByNumIdentificacion(query))
                .thenReturn(clientes);

        BuscarClientes usecase = new BuscarClientes();
        Assertions.assertEquals(true, usecase.medianteString(query, persistenciaCuenta).size() == 1);
    }
}
