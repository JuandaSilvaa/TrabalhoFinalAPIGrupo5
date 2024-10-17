package org.serratec.trabalhofinal.redesocialsimples.repository;

import org.serratec.trabalhofinal.redesocialsimples.entity.Relacionamento;
import org.serratec.trabalhofinal.redesocialsimples.entity.RelacionamentoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelacionamentoRepository extends JpaRepository<Relacionamento, RelacionamentoPK>{

}
