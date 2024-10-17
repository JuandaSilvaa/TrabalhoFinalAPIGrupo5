package org.serratec.trabalhofinal.redesocialsimples.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	@NotBlank(message = "Preencha o nome")
	@Size(max = 60)
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;

	@NotBlank(message = "Preencha com o sobrenome")
	@Size(max = 100)
	@Column(name = "sobrenome", nullable = false, length = 100)
	private String sobrenome;

	@NotBlank(message = "Preencha com o E-mail")
	@Size(max = 100)
	@Column(name = "email", unique = true, nullable = false, length = 100)
	private String email;

	@NotBlank(message = "Preencha com a senha")
	@Size(max = 100)
	@Column(name = "senha", nullable = false, length = 100)
	private String senha;

	@NotNull
	@Column
	private LocalDate data_nascimento;

	@OneToMany(mappedBy = "id.seguidor")
	private Set<Relacionamento> seguidores = new HashSet<>();

	@OneToMany(mappedBy = "id.seguido")
	private Set<Relacionamento> seguindo = new HashSet<>();

	public Usuario() {
	}

	public Usuario(Long id, String nome, String sobrenome, String email, String senha, LocalDate data_nascimento,
			Set<Relacionamento> seguidores, Set<Relacionamento> seguindo) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.data_nascimento = data_nascimento;
		this.seguidores = seguidores;
		this.seguindo = seguindo;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}
