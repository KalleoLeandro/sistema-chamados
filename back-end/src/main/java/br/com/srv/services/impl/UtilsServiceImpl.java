package br.com.srv.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.srv.exceptions.DefaultErrorException;
import br.com.srv.services.UtilsService;

@Service
public class UtilsServiceImpl implements UtilsService{
	
	private static final Logger logger = LoggerFactory.getLogger(UtilsServiceImpl.class);

	@Override
	public Boolean validarCpf(String cpf) {		
	    CPFValidator validador = new CPFValidator();
	    try {
	    	logger.info("Executando a assertValid");
	        validador.assertValid(cpf);
	        return true;  // CPF válido
	    } catch (InvalidStateException e) {
	        return false; // CPF inválido
	    } catch (Exception e) {
	        // Captura erros gerais inesperados
	    	throw new DefaultErrorException("Erro na execução da validação do cpf: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	


}
