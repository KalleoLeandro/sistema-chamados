package br.com.srv.services;

import br.com.srv.models.requests.LoginRequest;
import br.com.srv.models.responses.LoginResponse;

public interface LoginService {
	
	public LoginResponse validarLogin(LoginRequest loginRequest);	
	
	public Boolean validarToken(String token);

}
