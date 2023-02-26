package com.minegocio.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.entities.Direccion;
import com.minegocio.core.usecases.ActualizarCliente;
import com.minegocio.core.usecases.BuscarClientes;
import com.minegocio.core.usecases.CrearCliente;
import com.minegocio.core.usecases.CrearDireccion;
import com.minegocio.core.usecases.EliminarCliente;
import com.minegocio.core.usecases.ListarDirecciones;
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
                return response;
            } else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, cliente.toString());

        } catch (DataIntegrityViolationException e) {
            System.err.println(e);
            throw new ResponseStatusException(HttpStatus.SEE_OTHER, "Duplicado");
        }

    }

    @GetMapping(API_PATH + "/filtrar")
    public String buscar(@RequestParam(value = "query") String query) {
        try {
            BuscarClientes usecase = new BuscarClientes();
            return usecase.medianteString(query, persistenciaCuenta).toString();
        } catch (Exception e) {
            System.err.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
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
                return response;
            } else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, cliente.toString());

        } catch (Exception e) {
            System.err.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
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
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(API_PATH + "/{id}/direcciones")
    public String agregarDireccionAlCliente(@PathVariable(value = "id") int id, @RequestBody Direccion direccion) {
        try {
            String response;
            if (direccion.isValid()) {
                CrearDireccion usecase = new CrearDireccion();
                Cliente cliente = new Cliente();
                cliente.setId(String.valueOf(id));
                response = usecase.registrarNuevaDireccion(cliente, direccion, persistenciaCuenta).toString();
                return response;
            } else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, direccion.toString());

        } catch (Exception e) {
            System.err.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(API_PATH + "/{id}/direcciones")
    public String listarDireccionesDelCliente(@PathVariable(value = "id") int id) {
        try {
            ListarDirecciones usecase = new ListarDirecciones();
            Cliente cliente = new Cliente();
            cliente.setId(String.valueOf(id));
            return usecase.obtenerDireccionesDelCliente(cliente, persistenciaCuenta).toString();
        } catch (Exception e) {
            System.err.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
