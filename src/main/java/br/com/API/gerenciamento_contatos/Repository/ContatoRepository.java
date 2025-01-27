package br.com.API.gerenciamento_contatos.Repository;

import br.com.API.gerenciamento_contatos.Dto.Request.ContatoRequestDto;
import br.com.API.gerenciamento_contatos.Entity.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {

}
