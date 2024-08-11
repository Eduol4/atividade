package com.atividade1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atividade1.entities.VendaEntity;
import com.atividade1.services.VendaService;

@RestController
@RequestMapping("/venda")
public class VendaController {

	@Autowired
	private VendaService vendaService;
	
    @PostMapping("/realizarVendas")
    public VendaEntity criarVenda(@RequestBody VendaEntity vendaEntity) {
        return vendaService.salvarVenda(vendaEntity);
    }
    
    @GetMapping("/listarVendas")
    public List<VendaEntity> listarVendas() {
        return vendaService.listarVendas();
    }
    
    @GetMapping("/listarVendas/top10")
    public List<VendaEntity> listarTop10MaioresVendas() {
        return vendaService.listarTop10MaioresVendas();
    }
    
    @GetMapping("/vendas/cliente")
    public List<VendaEntity> listarVendasPorNomeCliente(@RequestParam String nome) {
        return vendaService.listarVendasPorNomeCliente(nome);
    }

    @GetMapping("/vendas/funcionario")
    public List<VendaEntity> listarVendasPorNomeFuncionario(@RequestParam String nome) {
        return vendaService.listarVendasPorNomeFuncionario(nome);
    }
}
