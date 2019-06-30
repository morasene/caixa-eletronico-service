package br.com.morasene.caixaeletronico.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CashOutValidate.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CashOut {

	String message() default "Value é obrigatório e deve ser maior que 0 (ZERO)";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
