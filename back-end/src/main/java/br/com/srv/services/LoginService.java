package br.com.srv.services;

import br.com.srv.models.requests.LoginRequest;
import br.com.srv.models.requests.TokenRequest;
import br.com.srv.models.responses.LoginResponse;

public interface LoginService {
	
	public LoginResponse validarLogin(LoginRequest loginRequest);	
	
	public Boolean validarToken(TokenRequest tokenRequest);

}
