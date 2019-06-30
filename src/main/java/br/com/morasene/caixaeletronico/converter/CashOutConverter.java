package br.com.morasene.caixaeletronico.converter;

import org.springframework.stereotype.Component;

import br.com.morasene.caixaeletronico.dto.CashOutDTO;
import br.com.morasene.caixaeletronico.dto.CashOutInput;
import br.com.morasene.caixaeletronico.dto.CashOutInputDTO;
import br.com.morasene.caixaeletronico.dto.CashOutOutput;

@Component
public class CashOutConverter {

	public CashOutOutput toOutput(CashOutDTO cashOut) {
		CashOutOutput out = new CashOutOutput();
		out.setBankNotes(cashOut.getBankNotes());
		return out;
	}
	
	public CashOutInputDTO toInputDTO(CashOutInput cashOut) {
		CashOutInputDTO in = new CashOutInputDTO();
		in.setValue(cashOut.getValue());
		return in;
	}
}
