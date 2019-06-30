package br.com.morasene.caixaeletronico.validate;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CashOutValidate implements ConstraintValidator<CashOut, BigDecimal> {

	@Override
	public void initialize(CashOut cashOut) {
	}

	@Override
	public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
		if (value == null || value.compareTo(BigDecimal.ZERO) <= 0)
			return false;
		return true;
	}

}
