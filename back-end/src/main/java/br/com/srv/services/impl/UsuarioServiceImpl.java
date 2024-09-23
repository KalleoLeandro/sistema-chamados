package br.com.srv.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.srv.entities.ContatoEntity;
import br.com.srv.entities.EnderecoEntity;
import br.com.srv.entities.LoginEntity;
import br.com.srv.entities.UsuarioEntity;
import br.com.srv.exceptions.DefaultErrorException;
import br.com.srv.models.requests.UsuarioRequest;
import br.com.srv.repositories.ContatoRepository;
import br.com.srv.repositories.EnderecoRepository;
import br.com.srv.repositories.LoginRepository;
import br.com.srv.repositories.UsuarioRepository;
import br.com.srv.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void gravarUsuario(UsuarioRequest usuarioRequest) {
		try {
			UsuarioEntity usuarioEntity = getUsuario(usuarioRequest);
			
			LoginEntity loginEntity = loginRepository.save(usuarioEntity.getLoginEntity());
			ContatoEntity contatoEntity = contatoRepository.save(usuarioEntity.getContatoEntity());
			EnderecoEntity enderecoEntity = enderecoRepository.save(usuarioEntity.getEnderecoEntity());
			
			usuarioEntity.setLoginEntity(loginEntity);
			usuarioEntity.setContatoEntity(contatoEntity);
			usuarioEntity.setEnderecoEntity(enderecoEntity);
			
			usuarioRepository.save(usuarioEntity);					
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na execução da validação do token: " + e, HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}

	private UsuarioEntity getUsuario(UsuarioRequest usuarioRequest) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setId(usuarioRequest.getId());
		usuarioEntity.setNome(usuarioRequest.getNome());
		usuarioEntity.setCpf(usuarioRequest.getCpf());
		usuarioEntity.setData_nascimento(usuarioRequest.getDataNascimento());
		usuarioEntity.setSexo(usuarioRequest.getSexo());
		EnderecoEntity enderecoEntity = new EnderecoEntity();
		enderecoEntity.setCep(usuarioRequest.getCep());
		enderecoEntity.setRua(usuarioRequest.getRua());
		enderecoEntity.setNumero(usuarioRequest.getNumero());
		enderecoEntity.setComplemento(usuarioRequest.getComplemento());
		enderecoEntity.setBairro(usuarioRequest.getBairro());
		enderecoEntity.setCidade(usuarioRequest.getCidade());
		enderecoEntity.setUf(usuarioRequest.getUf());
		usuarioEntity.setEnderecoEntity(enderecoEntity);
		ContatoEntity contatoEntity = new ContatoEntity();
		contatoEntity.setTelefone(usuarioRequest.getTelefone());
		contatoEntity.setCelular(usuarioRequest.getCelular());
		contatoEntity.setEmail(usuarioRequest.getEmail());
		usuarioEntity.setContatoEntity(contatoEntity);
		LoginEntity loginEntity = new LoginEntity();
		loginEntity.setLogin(usuarioRequest.getLogin());
		loginEntity.setSenha(usuarioRequest.getSenha());
		loginEntity.setPerfil(usuarioRequest.getPerfil());
		usuarioEntity.setLoginEntity(loginEntity);
		return usuarioEntity;
	}

}
