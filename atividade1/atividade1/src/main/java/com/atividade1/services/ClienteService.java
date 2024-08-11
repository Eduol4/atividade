package com.atividade1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade1.entities.ClienteEntity;
import com.atividade1.repository.ClienteRepository;

@Service
public class ClienteService {
	
   @Autowired
   private ClienteRepository clienteRepository;
   
   public ClienteEntity salvarCliente(ClienteEntity clienteEntity) {
       try {
    	   	return clienteRepository.save(clienteEntity);
	} catch (Exception e) {
			return new ClienteEntity();
	}
   }
   
   public List<ClienteEntity> listarClientes() {
       return clienteRepository.findAll();
   }
   
   public List<ClienteEntity> listarClientesPorIdade(Integer idadeMin, Integer idadeMax) {
       return clienteRepository.findByIdadeBetween(idadeMin, idadeMax);
   }
}
