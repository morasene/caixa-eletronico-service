package br.com.morasene.caixaeletronico.dto;

import java.math.BigDecimal;

import br.com.morasene.caixaeletronico.validate.CashOut;

public class CashOutInput {

	@CashOut
	BigDecimal value;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
