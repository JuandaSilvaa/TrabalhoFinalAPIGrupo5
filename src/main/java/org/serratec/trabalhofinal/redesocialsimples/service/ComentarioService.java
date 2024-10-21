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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    public List<ComentarioDTO> findAll() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        return comentarios.stream().map(ComentarioDTO::new).toList();
    }
 
    public ComentarioDTO buscarPorId(Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);
        return comentario.map(ComentarioDTO::new).orElse(null);
    }

    @Transactional
    public ComentarioDTO adicionar(ComentarioInserirDTO comentarioInserirDTO) {
        Optional<Postagem> postagem = postagemRepository.findById(comentarioInserirDTO.getPostagemId());
        if (postagem.isEmpty()) {
        	
        }
        
        Comentario comentario = new Comentario();
        comentario.setConteudo(comentarioInserirDTO.getConteudo());
        comentario.setDateCriacao(comentarioInserirDTO.getDataCriacao());
        comentario.setPostagem(postagem.get());
        comentario = comentarioRepository.save(comentario);
		return null;
    }

    public void deletar(Long id) {
        if (comentarioRepository.existsById(id)) {
            comentarioRepository.deleteById(id);
        }
    }
}
