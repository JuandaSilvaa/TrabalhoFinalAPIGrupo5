package org.serratec.trabalhofinal.redesocialsimples.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.serratec.trabalhofinal.redesocialsimples.dto.PostagemDTO;
import org.serratec.trabalhofinal.redesocialsimples.dto.PostagemInserirDTO;
import org.serratec.trabalhofinal.redesocialsimples.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	PostagemService postagemService;

	@GetMapping
	public ResponseEntity<List<PostagemDTO>> listar() {
		return ResponseEntity.ok(postagemService.findall());
	}

	@GetMapping("/{id}") 
	public ResponseEntity<PostagemDTO> buscar(@PathVariable Long id) {
	    Optional<PostagemDTO> postagemOpt = postagemService.buscarPorId(id);

	    if (postagemOpt.isPresent()) {
	        return ResponseEntity.ok(postagemOpt.get());
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
	public ResponseEntity<PostagemDTO> atualizar(@PathVariable Long id,
			@Valid @RequestBody PostagemInserirDTO postagemInserirDTO) {
		PostagemDTO postagemAtualizado = postagemService.atualizarPostagem(id, postagemInserirDTO);

		return ResponseEntity.ok(postagemAtualizado);
	}
	
	@GetMapping("/paginas")
	public ResponseEntity<Page<PostagemDTO>> listarPaginado(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 8) Pageable pageable) {
		Page<PostagemDTO> usuario = postagemService.paginacao(pageable);
		return ResponseEntity.ok(usuario);
	}

	@DeleteMapping("/{id}") 
	public ResponseEntity<Void> remover(@PathVariable Long id) {
	    Optional<PostagemDTO> postagemOpt = postagemService.buscarPorId(id);

	    if (postagemOpt.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    postagemService.deletar(id);
	    return ResponseEntity.noContent().build();
	}



}