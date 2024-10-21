package org.serratec.trabalhofinal.redesocialsimples.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.trabalhofinal.redesocialsimples.entity.Relacionamento;
import org.serratec.trabalhofinal.redesocialsimples.entity.RelacionamentoPK;
import org.serratec.trabalhofinal.redesocialsimples.entity.Usuario;
import org.serratec.trabalhofinal.redesocialsimples.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/{seguidorId}/{seguidoId}")
	public ResponseEntity<Relacionamento> pesquisar(@PathVariable Usuario seguidorId, @PathVariable Usuario seguidoId) {

		RelacionamentoPK relacionamentoPK = new RelacionamentoPK();
		relacionamentoPK.setSeguidor(seguidorId);
		relacionamentoPK.setSeguido(seguidoId);

		Optional<Relacionamento> relacionamentoOpt = relacionamentoRepository.findById(relacionamentoPK);
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

	@DeleteMapping("/{seguidorId}/{seguidoId}")
	public ResponseEntity<Void> remover(@PathVariable Usuario seguidorId, @PathVariable Usuario seguidoId) {

	    RelacionamentoPK relacionamentoPK = new RelacionamentoPK();
	    relacionamentoPK.setSeguidor(seguidorId);
	    relacionamentoPK.setSeguido(seguidoId);

	    if (!relacionamentoRepository.existsById(relacionamentoPK)) {
	        return ResponseEntity.notFound().build();
	    }

	    relacionamentoRepository.deleteById(relacionamentoPK);
	    return ResponseEntity.noContent().build();
	}

}
