package com.atividade1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade1.entities.ProdutoEntity;
import com.atividade1.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public ProdutoEntity salvarProduto(ProdutoEntity produtoEntity) {
        try {
        	return produtoRepository.save(produtoEntity);
		} catch (Exception e) {
			return new ProdutoEntity();
		}
    }
    
    public List<ProdutoEntity> listarProdutos() {
        return produtoRepository.findAll();
    }
    
    public List<ProdutoEntity> listarTop10ProdutosMaisCaros() {
        return produtoRepository.findTop10ByOrderByPrecoDesc();
    }
}
