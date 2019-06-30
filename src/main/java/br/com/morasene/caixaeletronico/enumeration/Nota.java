package br.com.morasene.caixaeletronico.enumeration;

import java.math.BigDecimal;

public enum Nota {

	CEM(BigDecimal.valueOf(100.00)), 
	CINQUENTA(BigDecimal.valueOf(50.00)), 
	VINTE(BigDecimal.valueOf(20.00)),
	DEZ(BigDecimal.valueOf(10.00));

	private BigDecimal value;

	Nota(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}
}
