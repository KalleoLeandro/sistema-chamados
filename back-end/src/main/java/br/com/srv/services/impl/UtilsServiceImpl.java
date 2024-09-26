package br.com.srv.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import br.com.caelum.stella.validation.CPFValidator;
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
	        return true;
	    } catch (Exception e) {	      
	    	return false;
	    }
	}
	
	


}
