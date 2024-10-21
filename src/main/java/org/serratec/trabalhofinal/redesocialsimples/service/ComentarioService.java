package org.serratec.trabalhofinal.redesocialsimples.service;

import java.util.List;
import java.util.Optional;

import org.serratec.trabalhofinal.redesocialsimples.dto.ComentarioDTO;
import org.serratec.trabalhofinal.redesocialsimples.dto.ComentarioInserirDTO;
import org.serratec.trabalhofinal.redesocialsimples.entity.Comentario;
import org.serratec.trabalhofinal.redesocialsimples.entity.Postagem;
import org.serratec.trabalhofinal.redesocialsimples.repository.ComentarioRepository;
import org.serratec.trabalhofinal.redesocialsimples.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PostagemRepository postagemRepository;

	public List<ComentarioDTO> findall() {
		List<Comentario> comentarios = comentarioRepository.findAll();
		List<ComentarioDTO> comentarioDTO = comentarios.stream().map(ComentarioDTO::new).toList();
		return comentarioDTO;
	}
 
	public Optional<ComentarioDTO> buscarPorId(Long id) {
	    return comentarioRepository.findById(id).map(ComentarioDTO::new);
	}
	
	public Page<ComentarioDTO> paginacao(Pageable pageable) {
		Page<Comentario> comentarios = comentarioRepository.findAll(pageable);
		List<ComentarioDTO> comentarioDTO = comentarios.stream().map(ComentarioDTO::new).toList();
		return new PageImpl<>(comentarioDTO, pageable, comentarios.getTotalElements());
	}
	
	@Transactional
	public ComentarioDTO adicionar(ComentarioInserirDTO comentarioInserirDTO) {
	    Long postagemId = comentarioInserirDTO.getPostagemId();
	    Optional<Postagem> postagem = postagemRepository.findById(postagemId);
	    if (postagem.isEmpty()) {
	        throw new EntityNotFoundException("Postagem não encontrada de ID: " + postagemId);
	    }
	    
	    Comentario comentario = new Comentario();
	    comentario.setConteudo(comentarioInserirDTO.getConteudo());
	    comentario.setDateCriacao(comentarioInserirDTO.getDataCriacao());
	    comentario.setPostagem(postagem.get());

	    comentario = comentarioRepository.save(comentario);
	    return new ComentarioDTO(comentario);
	}
	
	@Transactional
	public ComentarioDTO atualizarComentario(Long id, ComentarioInserirDTO comentarioInserirDTO) {
	    Comentario comentario = comentarioRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));

	    comentario.setConteudo(comentarioInserirDTO.getConteudo());
	    comentario.setDateCriacao(comentarioInserirDTO.getDataCriacao());

	    Comentario comentarioAtualizado = comentarioRepository.save(comentario);

	    return new ComentarioDTO(comentarioAtualizado);
	}

	public void deletar(Long id) {
		comentarioRepository.deleteById(id);
	}
}
