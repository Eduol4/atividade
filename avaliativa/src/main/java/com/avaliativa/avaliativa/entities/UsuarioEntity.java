package com.avaliativa.avaliativa.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
	@Column(name = "nomeUsuario")
    @NotBlank
    private String nomeUsuario;

    @Column(name = "email", unique = true)
    @NotBlank
    private String email;

    @Column(name = "senha")
    @NotBlank
    private String senha;

    @Column(name = "perfil")
    @NotBlank
    private String perfil;
    // "COLABORADOR", "ADMIN", "AVALIADOR"

    @ManyToMany(mappedBy = "jurados")
	private List<EventoEntity> eventos;
    
    @OneToMany(mappedBy = "colaborador")
    private List<IdeiaEntity> ideiasPostadas;
    
    @ManyToMany(mappedBy = "avaliadores")
    private List<IdeiaEntity> ideiasAvaliadas;
    
    public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public List<EventoEntity> getEvento() {
		return eventos;
	}

	public void setEvento(List<EventoEntity> evento) {
		this.eventos = evento;
	}
}
