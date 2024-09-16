package com.avaliativa.avaliativa.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ideia")
public class IdeiaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIdeia;

    @Column(name = "nomeIdeia")
    @NotBlank
    private String nome;

    @Column(name = "descricaoIdeia")
    @NotBlank
    @Size(max = 1000)
    private String descricaoIdeia;

    @Column(name = "impacto")
    @NotNull
    private Double impacto;

    @Column(name = "custo")
    @NotNull
    private Double custo;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private EventoEntity evento;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private UsuarioEntity colaborador;
    
    @ElementCollection
    private List<Double> notas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "ideia_jurados",
        joinColumns = @JoinColumn(name = "ideia_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<UsuarioEntity> avaliadores;

    private Double mediaNota;

	public Long getIdIdeia() {
		return idIdeia;
	}

	public void setIdIdeia(Long idIdeia) {
		this.idIdeia = idIdeia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricaoIdeia() {
		return descricaoIdeia;
	}

	public void setDescricaoIdeia(String descricaoIdeia) {
		this.descricaoIdeia = descricaoIdeia;
	}

	public Double getImpacto() {
		return impacto;
	}

	public void setImpacto(Double impacto) {
		this.impacto = impacto;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public EventoEntity getEvento() {
		return evento;
	}

	public void setEvento(EventoEntity evento) {
		this.evento = evento;
	}

	public UsuarioEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(UsuarioEntity colaborador) {
		this.colaborador = colaborador;
	}

	public List<UsuarioEntity> getAvaliadores() {
		return avaliadores;
	}

	public void setAvaliadores(List<UsuarioEntity> Avaliadores) {
		this.avaliadores = Avaliadores;
	}

	public Double getMediaNota() {
		return mediaNota;
	}

	public void setMediaNota(Double mediaNota) {
		this.mediaNota = mediaNota;
	}

	public List<Double> getNotas() {
		return notas;
	}

	public void setNotas(List<Double> notas) {
		this.notas = notas;
	}
	
}