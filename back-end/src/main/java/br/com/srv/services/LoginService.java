package br.com.srv.services;

import br.com.srv.entities.requests.LoginRequest;
import br.com.srv.models.responses.LoginResponse;

public interface LoginService {
	
	public LoginResponse ValidarLogin(LoginRequest loginRequest);	

}
