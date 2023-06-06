package br.com.sysprise.contato.model;

import br.com.sysprise.contato.validation.OneCannotBeNullAndEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@OneCannotBeNullAndEmpty(campos = {"email", "telefone"}, message = "Obrigatório o preenchimento do e-mail ou do telefone")
public record DadosCadastroContato(@Email
                                   String email,
                                   @Pattern(regexp = "\\d{8,13}", message = "O padrão esperado é de oito a treze dígitos sem ponto, parênteses ou traço!")
                                   String telefone,
                                   @Min(1)
                                   @NotNull(message = "Obrigatório o envio do ID da pessoa")
                                   Long pessoaId) {
}
