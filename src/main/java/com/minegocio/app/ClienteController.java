package com.minegocio.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.entities.Direccion;
import com.minegocio.core.usecases.ActualizarCliente;
import com.minegocio.core.usecases.BuscarClientes;
import com.minegocio.core.usecases.CrearCliente;
import com.minegocio.core.usecases.CrearDireccion;
import com.minegocio.core.usecases.EliminarCliente;
import com.minegocio.data.adapters.PersistenciaCuenta;

@RestController
public class ClienteController {
    private final String API_PATH = "/api/clientes";

    @Autowired
    private PersistenciaCuenta persistenciaCuenta;

    @PostMapping(API_PATH + "/registro")
    public String registro(@RequestBody Cliente cliente) {
        try {
            String response;
            if (cliente.isValid() && cliente.getDirecciones().get(0).isValid()) {
                CrearCliente usecase = new CrearCliente();
                response = "msg: " + usecase.guardarCuentaCreada(cliente, persistenciaCuenta);
            } else
                response = cliente.toString();

            return response;
        } catch (DataIntegrityViolationException e) {
            System.err.println(e);
            return "{ status: 303, error: Duplicado }";
        }

    }

    @GetMapping(API_PATH + "/filtrar")
    public String buscar(@RequestParam(value = "query") String query) {
        try {
            BuscarClientes usecase = new BuscarClientes();
            return usecase.medianteString(query, persistenciaCuenta).toString();
        } catch (Exception e) {
            System.err.println(e);
            return "{ status: 500, error: Internal Server Error }";
        }
    }

    @PutMapping(API_PATH + "/actualizar/{id}")
    public String actualizar(@PathVariable(value = "id") int id, @RequestBody Cliente cliente) {
        try {
            String response;
            if (cliente.isValid() && cliente.getDirecciones().get(0).isValid()) {
                ActualizarCliente usecase = new ActualizarCliente();
                cliente.setId(String.valueOf(id));
                response = usecase.actualizarCuenta(cliente, persistenciaCuenta).toString();
            } else
                response = cliente.toString();

            return response;
        } catch (Exception e) {
            System.err.println(e);
            return "{ status: 500, error: Internal Server Error }";
        }
    }

    @DeleteMapping(API_PATH + "/destruir/{id}")
    public String destruir(@PathVariable(value = "id") int id) {
        try {
            EliminarCliente usecase = new EliminarCliente();
            Cliente cliente = new Cliente();
            cliente.setId(String.valueOf(id));
            return "msg: " + usecase.eliminarCuenta(cliente, persistenciaCuenta);
        } catch (Exception e) {
            System.err.println(e);
            return "{ status: 500, error: Internal Server Error }";
        }
    }

    @PostMapping(API_PATH + "/{id}/direcciones")
    public String agregarDireccion(@PathVariable(value = "id") int id, @RequestBody Direccion direccion) {
        try {
            String response;
            if (direccion.isValid()) {
                CrearDireccion usecase = new CrearDireccion();
                Cliente cliente = new Cliente();
                cliente.setId(String.valueOf(id));
                response = usecase.registrarNuevaDireccion(cliente, direccion, persistenciaCuenta).toString();
            } else
                response = direccion.toString();

            return response;
        } catch (Exception e) {
            System.err.println(e);
            return "{ status: 500, error: Internal Server Error }";
        }
    }

}
