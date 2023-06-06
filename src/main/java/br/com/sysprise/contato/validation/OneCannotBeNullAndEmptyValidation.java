package br.com.sysprise.contato.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// O PRIMEIRO PARÂMETRO DO GENERIC É A ANOTAÇÃO
// O SEGUNDO PARÂMETRO SERIA O OBJETO QUE IREMOS UTILIZAR ESTA ANOTAÇÃO (CLASS, RECORD, ETC)
// PARA SER GENÉRICO É PASSADO O OBJECT
@Component
public class OneCannotBeNullAndEmptyValidation implements ConstraintValidator<OneCannotBeNullAndEmpty, Object> {

    private String[] camposParaVerificar;

    @Override
    public void initialize(OneCannotBeNullAndEmpty constraintAnnotation) {
        this.camposParaVerificar = constraintAnnotation.campos();
    }


    @Override
    @SneakyThrows
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        final BeanWrapperImpl beanWrapper = new BeanWrapperImpl(obj);
        return Arrays.stream(camposParaVerificar).map(campo -> (String) beanWrapper.getPropertyValue(campo))
                .filter(value -> value != null && !value.isEmpty()).count() > 0;
    }
}