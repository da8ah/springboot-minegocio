package com.minegocio.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minegocio.core.entities.Cliente;
import com.minegocio.core.usecases.CrearCliente;
import com.minegocio.data.adapters.PersistenciaCuenta;

@RestController
public class ClienteController {
    private final String API_PATH = "/api/clientes";

    @Autowired
    private PersistenciaCuenta persistenciaCuenta;

    @PostMapping(API_PATH + "/registro")
    public String registro(@RequestBody Cliente cliente) {
        CrearCliente usecase = new CrearCliente();
        return "msg: " + usecase.guardarCuentaCreada(cliente, persistenciaCuenta);
    }

    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}
