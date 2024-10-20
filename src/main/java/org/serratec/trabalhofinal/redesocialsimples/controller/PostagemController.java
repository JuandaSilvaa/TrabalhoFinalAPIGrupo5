package org.serratec.trabalhofinal.redesocialsimples.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.serratec.trabalhofinal.redesocialsimples.dto.PostagemDTO;
import org.serratec.trabalhofinal.redesocialsimples.dto.PostagemInserirDTO;
import org.serratec.trabalhofinal.redesocialsimples.entity.Postagem;
import org.serratec.trabalhofinal.redesocialsimples.repository.PostagemRepository;
import org.serratec.trabalhofinal.redesocialsimples.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

	@Autowired
	PostagemRepository postagemRepository;

	@Autowired
	PostagemService postagemService;

	@GetMapping
	public ResponseEntity<List<PostagemDTO>> listar() {
		return ResponseEntity.ok(postagemService.findall());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostagemDTO> pesquisar(@PathVariable Long id) {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		if (postagemOpt.isPresent()) {
			PostagemDTO postagemDTO = new PostagemDTO(postagemOpt.get());
			return ResponseEntity.ok(postagemDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<PostagemDTO> inserir(@RequestBody PostagemInserirDTO postagemInserirDTO) {
		PostagemDTO postagemDTO = postagemService.adicionar(postagemInserirDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(postagemDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(postagemDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Postagem> atualizar(@PathVariable Long id, @Valid @RequestBody Postagem postagem) {
		if (!postagemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		postagem.setId(id);
		postagem = postagemRepository.save(postagem);
		return ResponseEntity.ok(postagem);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!postagemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		postagemRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
