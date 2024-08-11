package com.atividade1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade1.entities.FuncionarioEntity;
import com.atividade1.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    public FuncionarioEntity salvarFuncionario(FuncionarioEntity funcionarioEntity) {
        try {
        	return funcionarioRepository.save(funcionarioEntity);
		} catch (Exception e) {
			return new FuncionarioEntity();
		}
    }
    
    public List<FuncionarioEntity> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }
}
