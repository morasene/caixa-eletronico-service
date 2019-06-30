package br.com.morasene.caixaeletronico.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.morasene.caixaeletronico.dto.CashOutDTO;
import br.com.morasene.caixaeletronico.dto.CashOutInputDTO;
import br.com.morasene.caixaeletronico.enumeration.Nota;

@Service
public class CaixaEletronicoService {

	public CashOutDTO cashOut(@Valid CashOutInputDTO cash) throws Exception {
		CashOutDTO cashOut = new CashOutDTO();
		BigDecimal remainder = cash.getValue();
		BigDecimal value = cash.getValue();
		Nota[] values = Nota.values();

		for (Nota nota : values) {
			BigDecimal bankNote = nota.getValue();
			if (bankNote.compareTo(value) > 0) {
				continue;
			}
			remainder = value.remainder(bankNote);
			BigInteger resultado = value.divide(bankNote, RoundingMode.DOWN).toBigInteger();
			cashOut.getBankNotes().put(nota, resultado);
			value = remainder;
		}
		if (remainder.compareTo(BigDecimal.ZERO) > 0) {
			throw new IllegalArgumentException("Valor informado é inválido. Preencha um valor multiplo de: ");
		}
		return cashOut;
	}
}
