package org.serratec.trabalhofinal.redesocialsimples.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.serratec.trabalhofinal.redesocialsimples.dto.ComentarioDTO;
import org.serratec.trabalhofinal.redesocialsimples.dto.ComentarioInserirDTO;
import org.serratec.trabalhofinal.redesocialsimples.service.ComentarioService;
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
@RequestMapping("/comentarios")
public class ComentarioController {
	
	@Autowired
	ComentarioService comentarioService;
	
	@GetMapping
	public ResponseEntity<List<ComentarioDTO>> listar() {
		return ResponseEntity.ok(comentarioService.findall());
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<ComentarioDTO> buscar(@PathVariable Long id) {
	    Optional<ComentarioDTO> comentarioOpt = comentarioService.buscarPorId(id);

	    if (comentarioOpt.isPresent()) {
	        return ResponseEntity.ok(comentarioOpt.get());
	    }

	    return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/paginas")
	public ResponseEntity<Page<ComentarioDTO>> listarPaginado(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 8) Pageable pageable) {
		Page<ComentarioDTO> usuario = comentarioService.paginacao(pageable);
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<ComentarioDTO> inserir(@RequestBody ComentarioInserirDTO comentarioInserirDTO) {
		ComentarioDTO comentarioDTO = comentarioService.adicionar(comentarioInserirDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(comentarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(comentarioDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ComentarioDTO> atualizar(@PathVariable Long id,
			@Valid @RequestBody ComentarioInserirDTO comentarioInserirDTO) {
		ComentarioDTO comentarioAtualizado = comentarioService.atualizarComentario(id, comentarioInserirDTO);

		return ResponseEntity.ok(comentarioAtualizado);
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<Void> remover(@PathVariable Long id) {
	    Optional<ComentarioDTO> comentarioOpt = comentarioService.buscarPorId(id);

	    if (comentarioOpt.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    comentarioService.deletar(id);
	    return ResponseEntity.noContent().build();
	}
}
