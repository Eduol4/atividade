package com.atividade1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atividade1.entities.ProdutoEntity;
import com.atividade1.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
    @PostMapping("/cadastrarProdutos")
    public ProdutoEntity criarProduto(@RequestBody ProdutoEntity produtoEntity) {
        return produtoService.salvarProduto(produtoEntity);
    }
    
    @GetMapping("/listarProdutos")
    public List<ProdutoEntity> listarProdutos() {
        return produtoService.listarProdutos();
    }
    
    @GetMapping("/listarProdutos/top10")
    public List<ProdutoEntity> listarTop10ProdutosMaisCaros() {
        return produtoService.listarTop10ProdutosMaisCaros();
    }
    
}
