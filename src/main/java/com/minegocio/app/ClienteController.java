package com.minegocio.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.usecases.BuscarClientes;
import com.minegocio.core.usecases.CrearCliente;
import com.minegocio.data.adapters.PersistenciaCuenta;

@RestController
public class ClienteController {
    private final String API_PATH = "/api/clientes";

    @Autowired
    private PersistenciaCuenta persistenciaCuenta;

    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping(API_PATH + "/registro")
    public String registro(@RequestBody Cliente cliente) {
        CrearCliente usecase = new CrearCliente();
        return "msg: " + usecase.guardarCuentaCreada(cliente, persistenciaCuenta);
    }

    @GetMapping(API_PATH + "/filtrar")
    public String buscar(@RequestParam(value = "query") String query) {
        BuscarClientes usecase = new BuscarClientes();
        return usecase.medianteString(query, persistenciaCuenta).toString();
    }

    // @DeleteMapping(API_PATH + "/destruir/{id}")
    // public String destruir(@PathVariable(value = "numIdentificacion") String
    // numIdentificacion) {
    // EliminarCliente usecase = new EliminarCliente();
    // Cliente cliente = new Cliente();
    // cliente.setNumIdentificacion(numIdentificacion);
    // return "msg: " + usecase.eliminarCuenta(cliente, persistenciaCuenta);
    // }

}
