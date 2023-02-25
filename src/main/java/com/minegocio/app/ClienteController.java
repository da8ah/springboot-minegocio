package com.minegocio.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.usecases.BuscarClientes;
import com.minegocio.core.usecases.CrearCliente;
import com.minegocio.core.usecases.EliminarCliente;
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
        String response;
        if (cliente.isValid() && cliente.getDirecciones().get(0).isValid()) {
            CrearCliente usecase = new CrearCliente();
            response = "msg: " + usecase.guardarCuentaCreada(cliente, persistenciaCuenta);
        } else
            response = cliente.toString();

        return response;
    }

    @GetMapping(API_PATH + "/filtrar")
    public String buscar(@RequestParam(value = "query") String query) {
        BuscarClientes usecase = new BuscarClientes();
        return usecase.medianteString(query, persistenciaCuenta).toString();
    }

    @DeleteMapping(API_PATH + "/destruir/{id}")
    public String destruir(@PathVariable(value = "id") int id) {
        EliminarCliente usecase = new EliminarCliente();
        Cliente cliente = new Cliente();
        cliente.setId(String.valueOf(id));
        return "msg: " + usecase.eliminarCuenta(cliente, persistenciaCuenta);
    }

}
