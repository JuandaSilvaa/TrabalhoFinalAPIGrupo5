package org.serratec.trabalhofinal.redesocialsimples.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.serratec.trabalhofinal.redesocialsimples.dto.UsuarioDTO;
import org.serratec.trabalhofinal.redesocialsimples.dto.UsuarioInserirDTO;
import org.serratec.trabalhofinal.redesocialsimples.dto.UsuarioPostagemDTO;
import org.serratec.trabalhofinal.redesocialsimples.entity.Usuario;
import org.serratec.trabalhofinal.redesocialsimples.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Login do usuario: " + details.getUsername());
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
		Optional<Usuario> usuarioOpt = usuarioService.buscar(id);
		if (usuarioOpt.isPresent()) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioOpt.get());
			return ResponseEntity.ok(usuarioDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/paginas")
	public ResponseEntity<Page<UsuarioDTO>> listarPaginado(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 8) Pageable pageable) {
		Page<UsuarioDTO> usuario = usuarioService.paginacao(pageable);
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/{id}/postagens")
    public ResponseEntity<List<UsuarioPostagemDTO>> getPostagensPorUsuario(@PathVariable("id") Long userId) {
        List<UsuarioPostagemDTO> postagens = usuarioService.buscarPostagensPorUsuario(userId);
        if (postagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(postagens);
    }

	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir(@Valid @RequestBody UsuarioInserirDTO usuarioInserirDTO) {
		UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id,
			@Valid @RequestBody UsuarioInserirDTO usuarioInserirDTO) {
		UsuarioDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioInserirDTO);

		return ResponseEntity.ok(usuarioAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioService.buscar(id);

		if (usuario.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}