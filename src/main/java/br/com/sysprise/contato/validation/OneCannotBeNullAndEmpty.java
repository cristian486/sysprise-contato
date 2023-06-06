package br.com.sysprise.contato.validation;

/*
 * Referências:
 * https://www.youtube.com/watch?v=NP0w86efeUU
 * https://stackoverflow.com/questions/54638255/spring-boot-validation-one-from-two-not-null
 * https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/?v=8.0#section-class-level-constraints
 * */

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE}) // ESTE CAMPO INDICA ONDE A ANOTAÇÃO PODERÁ SER UTILIZADA, NESTE CASO É UTILIZADO NA CLASSE
@Retention(RetentionPolicy.RUNTIME) // AQUI DEVE DIZER SE ELA IRÁ EXECUTAR EM TEMPO DE COMPILAÇÃO OU EXECUÇÃO
@Constraint(validatedBy = OneCannotBeNullAndEmptyValidation.class) // AQUI VEM A CLASSE QUE IRÁ CONTER A LÓGICA DE VALIDAÇÃO
public @interface OneCannotBeNullAndEmpty {
    String message() default "Obrigatório o preenchimento de um dos campos";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    String[] campos() default {};
}
