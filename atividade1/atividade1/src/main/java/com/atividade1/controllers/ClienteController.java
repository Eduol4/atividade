package com.atividade1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atividade1.entities.ClienteEntity;
import com.atividade1.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
    @Autowired
    private ClienteService clienteService;
    
    @PostMapping("/cadastrarClientes")
    public ClienteEntity criarCliente(@RequestBody ClienteEntity clienteEntity) {
        return clienteService.salvarCliente(clienteEntity);
    }
    
    @GetMapping("/listarClientes")
    public List<ClienteEntity> listarClientes() {
        return clienteService.listarClientes();
    }
    
    @GetMapping("/listarClientes/idade")
    public List<ClienteEntity> listarClientesPorIdade(@RequestParam Integer idadeMin, @RequestParam Integer idadeMax) {
        return clienteService.listarClientesPorIdade(idadeMin, idadeMax);
    }
}
