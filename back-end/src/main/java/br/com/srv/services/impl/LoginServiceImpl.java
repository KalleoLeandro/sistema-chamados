package br.com.srv.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.srv.entities.LoginEntity;
import br.com.srv.entities.UsuarioEntity;
import br.com.srv.exceptions.DefaultErrorException;
import br.com.srv.models.requests.LoginRequest;
import br.com.srv.models.responses.LoginResponse;
import br.com.srv.repositories.LoginRepository;
import br.com.srv.repositories.UsuarioRepository;
import br.com.srv.services.LoginService;
import br.com.srv.utils.DateUtils;
import br.com.srv.utils.JwtUtils;

@Service
public class LoginServiceImpl implements LoginService{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	public LoginResponse validarLogin(LoginRequest loginRequest) {
		try{
			logger.info("Executando o LoginRepository.existsByLoginAndSenha");			
			Optional<LoginEntity> loginOpt = loginRepository.findByLoginAndSenha(loginRequest.getLogin(), loginRequest.getSenha());
			LoginResponse response = new LoginResponse();
			if (loginOpt.isPresent()) {
			    LoginEntity loginEntity = loginOpt.get();			    
				response.setStatus(200);
				Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findByLoginEntityId(loginEntity.getId());
				if(usuarioOpt.isPresent()) {
					UsuarioEntity usuarioEntity = usuarioOpt.get();
					response.setUserName(usuarioEntity.getNome());			
					response.setToken(jwtUtils.generateToken(usuarioEntity.getNome(), loginEntity.getLogin()));
					response.setExpiration(DateUtils.converterData(jwtUtils.getExpirationDateFromToken(response.getToken())));
					return response;
				} else {
					response.setStatus(401);
					return response;
				}
			} else {
				response.setStatus(401);
				return response;
			}
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na execução da validação do login: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@Override
	public Boolean validarToken(String token) {
		try {
			if(token.equalsIgnoreCase("")) return false;
			logger.info("Executando o validateToken");
			return jwtUtils.validateToken(token) ? true : false;
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na execução da validação do token: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

}
