package com.atividade1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade1.entities.VendaEntity;
import com.atividade1.repository.VendaRepository;

@Service
public class VendaService {
	
    @Autowired
    private VendaRepository vendaRepository;
    
    public VendaEntity salvarVenda(VendaEntity vendaEntity) {
        try {
        	return vendaRepository.save(vendaEntity);
		} catch (Exception e) {
			return new VendaEntity();
		}
    }
    
    public List<VendaEntity> listarVendas() {
        return vendaRepository.findAll();
    }
    
    public List<VendaEntity> listarTop10MaioresVendas() {
        return vendaRepository.findTop10ByOrderByValorTotalDesc();
    }
    
    public List<VendaEntity> listarVendasPorNomeCliente(String nome) {
        return vendaRepository.findByCliente_NomeContaining(nome);
    }
    
    public List<VendaEntity> listarVendasPorNomeFuncionario(String nome) {
        return vendaRepository.findByFuncionario_NomeContaining(nome);
    }
}
