package br.com.morasene.caixaeletronico.dto;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import br.com.morasene.caixaeletronico.enumeration.Nota;

public class CashOutOutput {

	Map<Nota, BigInteger> bankNotes = new HashMap<Nota, BigInteger>();

	public Map<Nota, BigInteger> getBankNotes() {
		return bankNotes;
	}

	public void setBankNotes(Map<Nota, BigInteger> bankNotes) {
		this.bankNotes = bankNotes;
	}

}
