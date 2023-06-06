package br.com.sysprise.contato.service;

import br.com.sysprise.contato.model.Contato;
import br.com.sysprise.contato.model.DadosAtualizarContato;
import br.com.sysprise.contato.model.DadosCadastroContato;
import br.com.sysprise.contato.model.DadosListagemContato;
import br.com.sysprise.contato.repository.ContatoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public Contato findContatoById(Long id) {
        return contatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("O contato requisitado não foi encontrado"));
    }

    public List<Contato> findAllByPessoaId(Long id) {
        return contatoRepository.findAllByPessoaId(id);
    }

    public Page<DadosListagemContato> listar(Pageable pageable) {
        return contatoRepository.findAll(pageable).map(DadosListagemContato::new);
    }

    public DadosListagemContato detalhar(Long id) {
        Contato contato = this.findContatoById(id);
        return new DadosListagemContato(contato);
    }

    public DadosListagemContato cadastrar(DadosCadastroContato dadosCadastro) {
        Contato contato = new Contato(dadosCadastro.email(), dadosCadastro.telefone(), dadosCadastro.pessoaId());
        contatoRepository.save(contato);
        return new DadosListagemContato(contato);
    }

    public void cadastrar(pb.CriarContatoRequest criarContatoRequest) {
        System.out.print("Email: ");
        System.out.println(criarContatoRequest.getEmail());

        System.out.print("Telefone: ");
        System.out.println(criarContatoRequest.getTelefone());

        System.out.println("Tamanho do telefone: " + criarContatoRequest.getTelefone().length());
        System.out.println("Tamanho do email: " + criarContatoRequest.getEmail().length());

        Contato contato = new Contato(criarContatoRequest.getEmail(), criarContatoRequest.getTelefone(), criarContatoRequest.getPessoaId());
        contatoRepository.save(contato);
    }

    public DadosListagemContato atualizar(DadosAtualizarContato dadosAtualizar) {
        Contato contato = this.findContatoById(dadosAtualizar.id());
        contato.atualizarCadastro(dadosAtualizar);
        return new DadosListagemContato(contato);
    }

    public void atualizar(pb.Contato dadosAtualizar) {
        Contato contato = this.findContatoById(dadosAtualizar.getId());
        contato.atualizarCadastro(dadosAtualizar);
    }

    public void deletar(Long id) {
        Contato contato = this.findContatoById(id);
        contatoRepository.delete(contato);
    }

}
