package org.serratec.trabalhofinal.redesocialsimples.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "relacionamento")
public class Relacionamento {

	@EmbeddedId
	private RelacionamentoPK id = new RelacionamentoPK();

	@Column(name = "data_inicio_seguimento")
	private LocalDate dataInicioSeguimento;

	public Relacionamento() {
	}

	public Relacionamento(Usuario seguidor, Usuario seguido, LocalDate dataInicioSeguimento) {
		this.id.setSeguidor(seguidor);
		this.id.setSeguido(seguido);
		this.dataInicioSeguimento = dataInicioSeguimento;
	}

	public RelacionamentoPK getId() {
		return id;
	}

	public void setId(RelacionamentoPK id) {
		this.id = id;
	}

	public LocalDate getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setDataInicioSeguimento(LocalDate dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}
}
