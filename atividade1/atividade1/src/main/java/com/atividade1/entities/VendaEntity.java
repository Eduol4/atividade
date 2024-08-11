package com.atividade1.entities;

import com.atividade1.entities.ProdutoEntity;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class VendaEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private FuncionarioEntity funcionarioEntity;

    @ManyToMany
    @JoinTable(
        name = "venda_produto",
        joinColumns = @JoinColumn(name = "venda_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    
    private List<ProdutoEntity> produtos;

    private String observacoes;

    private Double valorTotal;

    //@PrePersist
    //@PreUpdate
    //private void calcularValorTotal() {
    //    this.valorTotal = produtos.stream().mapToDouble(ProdutoEntity::getPreco).sum();
    //}
    
    public void calcularValorTotal() {
        valorTotal = 0.0;

        for (ProdutoEntity produtoEntity : produtos) {
            valorTotal += produtoEntity.getPreco();
        }
    }

    @PrePersist
    @PreUpdate
    private void antesDeSalvar() {
        calcularValorTotal();
    }
}
