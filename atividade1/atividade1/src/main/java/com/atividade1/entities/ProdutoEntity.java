package com.atividade1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Table
@Entity(name = "produtos")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
	private String descricao;
    private Double preco;
    
    //Sim, eu sei que já coloquei @Setter e @Getter ali encima, mas por algum motivo, o "getPreco()" em 
    //VendaEntity estava dando erro, e desse jeito, acabou resolvendo
    public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
