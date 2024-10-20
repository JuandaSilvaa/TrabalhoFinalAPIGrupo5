package org.serratec.trabalhofinal.redesocialsimples.service;

import java.util.List;
import java.util.Optional;

import org.serratec.trabalhofinal.redesocialsimples.dto.PostagemDTO;
import org.serratec.trabalhofinal.redesocialsimples.dto.PostagemInserirDTO;
import org.serratec.trabalhofinal.redesocialsimples.entity.Postagem;
import org.serratec.trabalhofinal.redesocialsimples.entity.Usuario;
import org.serratec.trabalhofinal.redesocialsimples.repository.PostagemRepository;
import org.serratec.trabalhofinal.redesocialsimples.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<PostagemDTO> findall() {
		List<Postagem> postagens = postagemRepository.findAll();
		List<PostagemDTO> postagensDTO = postagens.stream().map(PostagemDTO::new).toList();
		return postagensDTO;
	}

	public PostagemDTO buscarPorId(Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		return postagem.map(PostagemDTO::new).orElse(null);
	}

	@Transactional
	public PostagemDTO adicionar(PostagemInserirDTO postagemInserirDTO) {
	    Optional<Usuario> usuarioOpt = usuarioRepository.findById(postagemInserirDTO.getUsuario().getId());
	    
	    if (usuarioOpt.isPresent()) {
	        Usuario usuario = usuarioOpt.get();  

	        Postagem postagem = new Postagem();
	        postagem.setUsuario(usuario); 
	        postagem.setConteudo(postagemInserirDTO.getConteudo());
	        postagem.setDataCriacao(postagemInserirDTO.getDatacriacao());

	        postagem = postagemRepository.save(postagem);

	        return new PostagemDTO(postagem);
	    } else {
	        throw new EntityNotFoundException("Usuário não encontrado");
	    }
	}


	public void deletar(Long id) {
		postagemRepository.deleteById(id);
	}
}