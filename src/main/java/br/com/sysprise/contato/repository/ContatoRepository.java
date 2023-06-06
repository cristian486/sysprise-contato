package br.com.sysprise.contato.repository;

import br.com.sysprise.contato.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    List<Contato> findAllByPessoaId(Long id);
}
