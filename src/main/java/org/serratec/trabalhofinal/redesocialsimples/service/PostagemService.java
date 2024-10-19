package org.serratec.trabalhofinal.redesocialsimples.service;

import java.util.List;
import java.util.Optional;

import org.serratec.trabalhofinal.redesocialsimples.dto.PostagemDTO;
import org.serratec.trabalhofinal.redesocialsimples.dto.PostagemInserirDTO;
import org.serratec.trabalhofinal.redesocialsimples.entity.Postagem;
import org.serratec.trabalhofinal.redesocialsimples.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	public List<PostagemDTO> findall() {
		List<Postagem> postagens = postagemRepository.findAll();
		List<PostagemDTO> postagensDTO = postagens.stream().map(PostagemDTO::new).toList();
		return postagensDTO;
	}

	public PostagemDTO buscarPorId(Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		return postagem.map(PostagemDTO::new).orElse(null);
	}

	public PostagemDTO adicionar(PostagemInserirDTO postagemInserirDTO) {
		Postagem postagem = new Postagem();
		postagem.setConteudo(postagemInserirDTO.getConteudo());
		postagem.setDataCriacao(postagemInserirDTO.getDatacriacao());
		postagem = postagemRepository.save(postagem);
		return new PostagemDTO(postagem);
	}

	public void deletar(Long id) {
		postagemRepository.deleteById(id);
	}
}