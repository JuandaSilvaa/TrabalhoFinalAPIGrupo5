package org.serratec.trabalhofinal.redesocialsimples.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "relacionamento")
public class Relacionamento {

	@EmbeddedId
	private RelacionamentoPK id = new RelacionamentoPK();
	
	@ManyToOne
	@JoinColumn(name = "seguidor_id", insertable = false, updatable = false)
	private Usuario seguidor;
	
	@ManyToOne
	@JoinColumn(name = "seguido_id", insertable = false, updatable = false)
	private Usuario seguido;
	
	@NotBlank
	@Column(name = "data_inicio_seguimento", nullable = false)
	private LocalDate dataInicioSeguimento;

	public Relacionamento() {
	}

	public Relacionamento(RelacionamentoPK id, Usuario seguidor, Usuario seguido, LocalDate dataInicioSeguimento) {
		super();
		this.id = id;
		this.seguidor = seguidor;
		this.seguido = seguido;
		this.dataInicioSeguimento = dataInicioSeguimento;
	}

	public RelacionamentoPK getId() {
		return id;
	}

	public void setId(RelacionamentoPK id) {
		this.id = id;
	}

	public Usuario getSeguidor() {
		return seguidor;
	}

	public void setSeguidor(Usuario seguidor) {
		this.seguidor = seguidor;
	}

	public Usuario getSeguido() {
		return seguido;
	}

	public void setSeguido(Usuario seguido) {
		this.seguido = seguido;
	}

	public LocalDate getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setDataInicioSeguimento(LocalDate dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}
	
}
