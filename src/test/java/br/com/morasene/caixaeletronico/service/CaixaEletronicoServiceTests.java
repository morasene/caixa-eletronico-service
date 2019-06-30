package br.com.morasene.caixaeletronico.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.morasene.caixaeletronico.dto.CashOutDTO;
import br.com.morasene.caixaeletronico.dto.CashOutInputDTO;
import br.com.morasene.caixaeletronico.enumeration.Nota;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CaixaEletronicoServiceTests {

	@InjectMocks
	private CaixaEletronicoService service;

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void validarSucesso() throws Exception {
		CashOutInputDTO dto = new CashOutInputDTO();
		dto.setValue(BigDecimal.TEN);
		validator(dto, 0);
		CashOutDTO cashOut = service.cashOut(dto);
		Map<Nota, BigInteger> bankNotes = cashOut.getBankNotes();
		assertTrue(bankNotes.size() > 0);
	}

	@Test
	public void validarSucessoDez() throws Exception {
		CashOutInputDTO dto = new CashOutInputDTO();
		dto.setValue(BigDecimal.TEN);
		validator(dto, 0);
		CashOutDTO cashOut = service.cashOut(dto);
		Map<Nota, BigInteger> bankNotes = cashOut.getBankNotes();
		BigInteger bigInteger = bankNotes.get(Nota.DEZ);
		assertTrue(bankNotes.size() == 1);
		assertTrue(bigInteger.compareTo(BigInteger.ONE) == 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validarSucessoOnze() throws Exception {
		CashOutInputDTO dto = new CashOutInputDTO();
		dto.setValue(BigDecimal.valueOf(11.00));
		validator(dto, 0);
		service.cashOut(dto);
	}

	@Test
	public void validarFalhaZero() throws Exception {
		CashOutInputDTO dto = new CashOutInputDTO();
		dto.setValue(BigDecimal.ZERO);
		validator(dto, 1);
	}

	private void validator(CashOutInputDTO dto, int expected) {
		Set<ConstraintViolation<CashOutInputDTO>> constraintViolations = validator.validate(dto);
		assertEquals(expected, constraintViolations.size());
	}

	@Test
	public void validarFalhaNulo() throws Exception {
		CashOutInputDTO dto = new CashOutInputDTO();
		validator(dto, 1);
	}
}
