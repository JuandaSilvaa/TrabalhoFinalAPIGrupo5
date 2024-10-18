package org.serratec.trabalhofinal.redesocialsimples.dto;

import java.time.LocalDate;
import java.util.Set;

import org.serratec.trabalhofinal.redesocialsimples.entity.Relacionamento;
import org.serratec.trabalhofinal.redesocialsimples.entity.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String sobrenome;
	private LocalDate data_nascimento;
	private String email;
	private Set<Relacionamento> seguidores;
	private Set<Relacionamento> seguindo;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, String nome, String sobrenome, LocalDate data_nascimento, String email,
			Set<Relacionamento> seguidores, Set<Relacionamento> seguindo) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.data_nascimento = data_nascimento;
		this.email = email;
		this.seguidores = seguidores;
		this.seguindo = seguindo;
	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.data_nascimento = usuario.getData_nascimento();
		this.email = usuario.getEmail();
		this.seguidores = usuario.getSeguidores();
		this.seguindo = usuario.getSeguindo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public Set<Relacionamento> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(Set<Relacionamento> seguidores) {
		this.seguidores = seguidores;
	}

	public Set<Relacionamento> getSeguindo() {
		return seguindo;
	}

	public void setSeguindo(Set<Relacionamento> seguindo) {
		this.seguindo = seguindo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
