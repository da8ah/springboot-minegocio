package com.minegocio.core.usecases;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.minegocio.core.entities.Cliente;
import com.minegocio.data.adapters.ClienteRepository;
import com.minegocio.data.adapters.PersistenciaCuenta;
import com.minegocio.data.entities.ClienteEntity;
import com.minegocio.data.entities.DireccionEntity;

public class UseCasesTest {

    static ClienteRepository clienteRepository = Mockito.mock(ClienteRepository.class);
    PersistenciaCuenta persistenciaCuenta = new PersistenciaCuenta(clienteRepository);

    // Data
    static ClienteEntity clienteEntity1 = new ClienteEntity(
            1,
            "DNI",
            "1000000001",
            "Annie Andrade",
            "annie.andrade@email.com",
            "+593900000001");
    static ClienteEntity clienteEntity2 = new ClienteEntity(
            2,
            "RUC",
            "1000000001321",
            "Alondra Guzmán",
            "alondra.guzman@mail.com",
            "+593 900000002");
    // ClienteEntity.set(DireccionEntity)
    static ArrayList<DireccionEntity> direcciones1 = new ArrayList<>();
    static ArrayList<DireccionEntity> direcciones2 = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        direcciones1.add(new DireccionEntity(clienteEntity1, 1, "Loja", "Loja", "Principal y Secundaria"));
        clienteEntity1.setDirecciones(direcciones1);

        direcciones2.add(new DireccionEntity(clienteEntity2, 2, "Galápagos", "Puerto Baquerizo Moreno",
                "Av. Principal y Ruta 09"));
        clienteEntity2.setDirecciones(direcciones2);
    }

    @Test
    void testGuardarCuentaCreada() {
        // Save Clientes 1 & 2
        Mockito.when(clienteRepository.save(any())).thenReturn(clienteEntity1, clienteEntity2);

        Cliente cliente1 = clienteEntity1.toClienteConUnaDireccion();
        Cliente cliente2 = clienteEntity2.toClienteConUnaDireccion();

        if (cliente1.isValid() && cliente1.getDirecciones().get(0).isValid()) {
            Assertions.assertEquals(true,
                    new CrearCliente().guardarCuentaCreada(cliente1, persistenciaCuenta));
        } else
            Assertions.fail("Input Validation Failed");

        if (cliente2.isValid() && cliente2.getDirecciones().get(0).isValid()) {
            Assertions.assertEquals(true,
                    new CrearCliente().guardarCuentaCreada(cliente1, persistenciaCuenta));
        } else
            Assertions.fail("Input Validation Failed");

        Cliente cliente3 = new Cliente("",
                0,
                "09",
                "As",
                "correo",
                "movil");
        if (cliente3.isValid() && cliente3.getDirecciones().get(0).isValid()) {
            Assertions.assertEquals(true,
                    new CrearCliente().guardarCuentaCreada(
                            cliente1,
                            persistenciaCuenta));
        } else
            Assertions.assertFalse(cliente3.isValid());
    }

    @Test
    void testFiltrarCuentasByNumIdentificacion() {
        // FiltrarCuentas return ArrayList<Cliente>
        ArrayList<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(clienteEntity1);
        clientes.add(clienteEntity2);
        Mockito.when(clienteRepository.findByNumIdentificacion("10"))
                .thenReturn(clientes);

        Assertions.assertEquals(true, new BuscarClientes().medianteString("10", persistenciaCuenta).size() == 2);
    }

    @Test
    void testFiltrarCuentasByNombres() {
        // FiltrarCuentas return ArrayList<Cliente>
        ArrayList<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(clienteEntity1);
        clientes.add(clienteEntity2);
        Mockito.when(clienteRepository.findByNombres("A")).thenReturn(clientes);

        Assertions.assertEquals(true, new BuscarClientes().medianteString("A", persistenciaCuenta).size() == 2);
    }

    @Test
    void testActualizarCuenta() {
        // Update Cliente1
        clienteEntity1.setNombres("Anny Andrade");
        clienteEntity1.setCorreo("anny.andrade@email.com");
        Mockito.when(clienteRepository.save(any())).thenReturn(clienteEntity1);

        Assertions.assertEquals(true, new ActualizarCliente()
                .actualizarCuenta(clienteEntity1.toCliente(), persistenciaCuenta).getNombres() == "Anny Andrade");
    }

    @Test
    void testEliminarCuenta() {
        // Delete Cliente2
        Mockito.doNothing().when(clienteRepository).delete(clienteEntity2);

        Assertions.assertEquals(true,
                new EliminarCliente().eliminarCuenta(clienteEntity2.toCliente(), persistenciaCuenta));
    }

    @Test
    void testRegistrarNuevaDireccion() {
        // Cliente1.add(Direccion)
        direcciones1.add(new DireccionEntity(clienteEntity1, 3, "Pichincha", "Quito", "Parque de las Iguanas"));
        Mockito.when(clienteRepository.save(any())).thenReturn(clienteEntity1);
        Mockito.when(clienteRepository.findById(any())).thenReturn(Optional.of(clienteEntity1));

        Assertions.assertEquals(true,
                new CrearDireccion().registrarNuevaDireccion(clienteEntity1.toCliente(),
                        clienteEntity1.getDirecciones().get(1).toDireccion(),
                        persistenciaCuenta).getDirecciones().size() == 2);
    }

    @Test
    void testObtenerDirecciones() {
        // Cliente1.add(Direccion)
        direcciones1.add(new DireccionEntity(clienteEntity1, 3, "Pichincha", "Quito", "Parque de las Iguanas"));
        // Return All Direcciones
        Mockito.when(clienteRepository.findById(any())).thenReturn(Optional.of(clienteEntity1));

        Assertions.assertEquals(true,
                new ListarDirecciones().obtenerDireccionesDelCliente(clienteEntity1.toCliente(), persistenciaCuenta)
                        .getDirecciones().size() == 2);
    }

}
