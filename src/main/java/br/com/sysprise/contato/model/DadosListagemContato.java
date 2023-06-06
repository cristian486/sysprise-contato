package br.com.sysprise.contato.model;

public record DadosListagemContato(Long id, String email, String telefone, Long pessoaId) {

    public DadosListagemContato(Contato contato) {
        this(contato.getId(), contato.getEmail(), contato.getTelefone(), contato.getPessoaId());
    }
}
