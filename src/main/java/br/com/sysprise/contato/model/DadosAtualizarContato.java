package br.com.sysprise.contato.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;



public record DadosAtualizarContato(@Min(1)
                                    Long id,
                                    @Email
                                    String email,
                                    @Pattern(regexp = "\\d{8,13}", message = "O padrão esperado é de oito a treze dígitos sem ponto, parênteses ou traço!")
                                    String telefone,
                                    Boolean remover) {

    public boolean deveRemover() {
        return this.remover != null && this.remover;
    }
}
