package br.com.srv.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.srv.entities.LoginEntity;
import br.com.srv.exceptions.DefaultErrorException;
import br.com.srv.models.requests.LoginRequest;
import br.com.srv.models.requests.TokenRequest;
import br.com.srv.models.responses.LoginResponse;
import br.com.srv.repositories.LoginRepository;
import br.com.srv.services.LoginService;
import br.com.srv.utils.DateUtils;
import br.com.srv.utils.JwtUtils;

@Service
public class LoginServiceImpl implements LoginService{
	
	 private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginRepository repository;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	public LoginResponse validarLogin(LoginRequest loginRequest) {
		try{
			logger.info("Executando o LoginRepository.existsByLoginAndSenha");			
			Optional<LoginEntity> usuarioOpt = repository.findByLoginAndSenha(loginRequest.getLogin(), loginRequest.getSenha());
			LoginResponse response = new LoginResponse();
			if (usuarioOpt.isPresent()) {
			    LoginEntity entity = usuarioOpt.get();
				response.setStatus(200);
				response.setUserName(entity.getNome());				
				response.setToken(jwtUtils.generateToken(entity));
				response.setExpiration(DateUtils.converterData(jwtUtils.getExpirationDateFromToken(response.getToken())));
				return response;
			} else {
				response.setStatus(401);
				return response;
			}
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na execução da validação do login: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@Override
	public Boolean validarToken(TokenRequest tokenRequest) {
		try {
			if(tokenRequest.getToken().equalsIgnoreCase("")) return false;
			return jwtUtils.validateToken(tokenRequest) ? true : false;
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na execução da validação do token: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

}
