package br.com.srv.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.srv.services.impl.UtilsServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
public class UtilsServiceTest {

	@InjectMocks
	private UtilsServiceImpl service;

	@Mock
	private CPFValidator cpfValidator;

	@BeforeEach
	private void setUp() {
		MockitoAnnotations.openMocks(this);
		
	}

	@Test
    public void testValidarCpfTrue() throws Exception {
        
        String cpf = "22233344405";
        
        //Mockito.doNothing().when(cpfValidator).assertValid(anyString());
        
        boolean resultado = service.validarCpf(cpf);
        
        Assertions.assertTrue(resultado);
    }
	
	@Test
    public void testValidarCpfErro() throws Exception {
        
        String cpf = "";
        
        boolean resultado = service.validarCpf(cpf);
        
        Assertions.assertFalse(resultado);
    }

}
