package org.serratec.trabalhofinal.redesocialsimples.service;

import java.util.List;
import java.util.Optional;

import org.serratec.trabalhofinal.redesocialsimples.dto.UsuarioDTO;
import org.serratec.trabalhofinal.redesocialsimples.dto.UsuarioInserirDTO;
import org.serratec.trabalhofinal.redesocialsimples.entity.Usuario;
import org.serratec.trabalhofinal.redesocialsimples.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<UsuarioDTO> findall() {
		List<Usuario> usuario = usuarioRepository.findAll();
		List<UsuarioDTO> usuarioDTO = usuario.stream().map(UsuarioDTO::new).toList();
		return usuarioDTO;
	}

	public UsuarioDTO buscarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.map(UsuarioDTO::new).orElse(null);
	}

	public UsuarioDTO adicionar(UsuarioInserirDTO usuarioInserirDTO) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setSobrenome(usuarioInserirDTO.getSobrenome());
		usuario.setData_nascimento(usuarioInserirDTO.getData_nascimento());
		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}

	public void deletar(Long id) {
		usuarioRepository.deleteById(id);
	}
}