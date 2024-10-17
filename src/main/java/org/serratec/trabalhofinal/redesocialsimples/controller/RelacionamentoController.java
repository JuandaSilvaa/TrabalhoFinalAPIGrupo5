package org.serratec.trabalhofinal.redesocialsimples.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.trabalhofinal.redesocialsimples.entity.Relacionamento;
import org.serratec.trabalhofinal.redesocialsimples.entity.RelacionamentoPK;
import org.serratec.trabalhofinal.redesocialsimples.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/relacionamentos")
public class RelacionamentoController {
	
	@Autowired
	RelacionamentoRepository relacionamentoRepository;
	
	@GetMapping
	public ResponseEntity<List<Relacionamento>> listar() {
		return ResponseEntity.ok(relacionamentoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Relacionamento> pesquisar(@PathVariable RelacionamentoPK id) {
		Optional<Relacionamento> relacionamentoOpt = relacionamentoRepository.findById(id);
		if (relacionamentoOpt.isPresent()) {
			Relacionamento relacionamento = relacionamentoOpt.get();
			return ResponseEntity.ok(relacionamento);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Relacionamento inserir(@Valid @RequestBody Relacionamento relacionamento) {
		relacionamento = relacionamentoRepository.save(relacionamento);
		return relacionamento;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Relacionamento> atualizar(@PathVariable RelacionamentoPK id, @Valid @RequestBody Relacionamento relacionamento) {
		if (!relacionamentoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		relacionamento.setId(id);
		relacionamento = relacionamentoRepository.save(relacionamento);
		return ResponseEntity.ok(relacionamento);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable RelacionamentoPK id) {
		if(!relacionamentoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		relacionamentoRepository.deleteById(id);
		return ResponseEntity.noContent().build(); 
	}
}
