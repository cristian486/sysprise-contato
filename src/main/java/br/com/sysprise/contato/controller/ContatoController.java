package br.com.sysprise.contato.controller;

import br.com.sysprise.contato.model.DadosAtualizarContato;
import br.com.sysprise.contato.model.DadosCadastroContato;
import br.com.sysprise.contato.model.DadosListagemContato;
import br.com.sysprise.contato.service.ContatoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/contato")
@AllArgsConstructor
public class ContatoController {

    private final ContatoService contatoService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemContato>> listar(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<DadosListagemContato> dadosListagem = contatoService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemContato> detalhar(@PathVariable("id") Long id) {
        DadosListagemContato dadosDetalhamento = contatoService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemContato> cadastrar(@RequestBody DadosCadastroContato dadosCadastro, UriComponentsBuilder componentsBuilder) {
        DadosListagemContato dadosDetalhamento = contatoService.cadastrar(dadosCadastro);
        URI uri = componentsBuilder.path("/contato/{id}").buildAndExpand(dadosDetalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemContato> atualizar(@RequestBody DadosAtualizarContato dadosAtualizar) {
        DadosListagemContato dadosDetalhamento = contatoService.atualizar(dadosAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
        contatoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
