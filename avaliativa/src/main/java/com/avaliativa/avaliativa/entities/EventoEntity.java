package com.avaliativa.avaliativa.entities;

import java.util.List;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "evento")
public class EventoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @Column(name = "nomeEvento")
    @NotBlank
    private String nome;

    @Column(name = "descricaoEvento")
    @NotBlank
    private String descricaoEvento;

    @Column(name = "dataInicio")
    @NotNull
    private LocalDate dataInicio;

    @Column(name = "dataFim")
    @NotNull
    private LocalDate dataFim;

    @Column(name = "dataAvaliacaoJurados")
    @NotNull
    private LocalDate dataJurados;

    @Column(name = "dataAvalicaoPopular")
    @NotNull
    private LocalDate dataPopular;

    @ManyToMany
    @JoinTable(
        name = "evento_jurados",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<UsuarioEntity> jurados;

    @OneToMany(mappedBy = "evento")
    private List<IdeiaEntity> ideias;

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricaoEvento() {
		return descricaoEvento;
	}

	public void setDescricaoEvento(String descricaoEvento) {
		this.descricaoEvento = descricaoEvento;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDate getDataJurados() {
		return dataJurados;
	}

	public void setDataJurados(LocalDate dataJurados) {
		this.dataJurados = dataJurados;
	}

	public LocalDate getDataPopular() {
		return dataPopular;
	}

	public void setDataPopular(LocalDate dataPopular) {
		this.dataPopular = dataPopular;
	}

	public List<UsuarioEntity> getJurados() {
		return jurados;
	}

	public void setJurados(List<UsuarioEntity> jurados) {
		this.jurados = jurados;
	}

	public List<IdeiaEntity> getIdeias() {
		return ideias;
	}

	public void setIdeias(List<IdeiaEntity> ideias) {
		this.ideias = ideias;
	}
}
