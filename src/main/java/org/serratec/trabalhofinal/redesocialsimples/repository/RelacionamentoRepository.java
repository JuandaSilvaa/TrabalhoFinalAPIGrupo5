package org.serratec.trabalhofinal.redesocialsimples.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelacionamentoRepository extends JpaRepository<Relacionamento, Long>{

}
