package br.com.morasene.caixaeletronico.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.morasene.caixaeletronico.converter.CashOutConverter;
import br.com.morasene.caixaeletronico.dto.CashOutInput;
import br.com.morasene.caixaeletronico.dto.CashOutOutput;
import br.com.morasene.caixaeletronico.service.CaixaEletronicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
public class CaixaEletronicoAPI {

	@Autowired
	private CaixaEletronicoService service;

	@Autowired
	private CashOutConverter converter; 
	
	@PostMapping("/cashOut")
	@ApiOperation(value = "CashOut", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Valor sacado com sucesso.", response = CashOutOutput.class)
		, @ApiResponse(code = 406, message = "Valor é obrigatório e deve ser maior que 0 (ZERO).", response = CashOutOutput.class)
		})
	public CashOutOutput sacar(@RequestBody @Valid CashOutInput cashOut) throws Exception {
		return converter.toOutput(service.cashOut(converter.toInputDTO(cashOut)));
	}
}
