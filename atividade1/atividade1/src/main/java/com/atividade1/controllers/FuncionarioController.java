package com.atividade1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atividade1.entities.FuncionarioEntity;
import com.atividade1.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
    private FuncionarioService funcionarioService;
	
    @PostMapping("/cadastrarFuncionarios")
    public FuncionarioEntity criarFuncionario(@RequestBody FuncionarioEntity funcionarioEntity) {
        return funcionarioService.salvarFuncionario(funcionarioEntity);
    }
    
    @GetMapping("/listarFuncionarios")
    public List<FuncionarioEntity> listarFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }
}
