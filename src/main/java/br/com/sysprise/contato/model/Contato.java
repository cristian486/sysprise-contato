package br.com.sysprise.contato.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "email")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String telefone;
    private Long pessoaId;

    public Contato(String email, String telefone, Long pessoaId) {
        this.email = email;
        this.telefone = telefone;
        this.pessoaId = pessoaId;
    }

    public void atualizarCadastro(DadosAtualizarContato dadosAtualizar) {
        if(dadosAtualizar.telefone() != null && !dadosAtualizar.telefone().isEmpty())
            this.telefone = dadosAtualizar.telefone();

        if(dadosAtualizar.email() != null && !dadosAtualizar.email().isEmpty())
            this.email = dadosAtualizar.email();
    }

    public void atualizarCadastro(pb.Contato dadosAtualizar) {
        if(!dadosAtualizar.getTelefone().isEmpty())
            this.telefone = dadosAtualizar.getTelefone();

        if(!dadosAtualizar.getEmail().isEmpty())
            this.email = dadosAtualizar.getEmail();
    }
}
