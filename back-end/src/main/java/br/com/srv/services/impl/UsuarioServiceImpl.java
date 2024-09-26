package br.com.srv.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.srv.entities.ContatoEntity;
import br.com.srv.entities.EnderecoEntity;
import br.com.srv.entities.LoginEntity;
import br.com.srv.entities.UsuarioEntity;
import br.com.srv.exceptions.DefaultErrorException;
import br.com.srv.models.requests.UsuarioRequest;
import br.com.srv.models.responses.UsuarioResponse;
import br.com.srv.repositories.ContatoRepository;
import br.com.srv.repositories.EnderecoRepository;
import br.com.srv.repositories.LoginRepository;
import br.com.srv.repositories.UsuarioRepository;
import br.com.srv.services.UsuarioService;
import br.com.srv.utils.DateUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService {

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
			if (usuarioRequest.getId() != null) {
				Optional<UsuarioEntity> usOptional = usuarioRepository.findById(usuarioEntity.getId());
				if (usOptional.isPresent()) {
					UsuarioEntity updEntity = usOptional.get();
					usuarioEntity.getLoginEntity().setId(updEntity.getLoginEntity().getId());
					usuarioEntity.getEnderecoEntity().setId(updEntity.getEnderecoEntity().getId());
					usuarioEntity.getContatoEntity().setId(updEntity.getContatoEntity().getId());

					usuarioEntity.setLoginEntity(updEntity.getLoginEntity());
					usuarioEntity.setContatoEntity(updEntity.getContatoEntity());
					usuarioEntity.setEnderecoEntity(updEntity.getEnderecoEntity());

				}
			}
			LoginEntity loginEntity = loginRepository.save(usuarioEntity.getLoginEntity());
			ContatoEntity contatoEntity = contatoRepository.save(usuarioEntity.getContatoEntity());
			EnderecoEntity enderecoEntity = enderecoRepository.save(usuarioEntity.getEnderecoEntity());

			usuarioEntity.setLoginEntity(loginEntity);
			usuarioEntity.setContatoEntity(contatoEntity);
			usuarioEntity.setEnderecoEntity(enderecoEntity);

			usuarioRepository.save(usuarioEntity);
		} catch (

		Exception e) {
			throw new DefaultErrorException("Erro na execução da gravação/atualização do usuário: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
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

	@Override
	public List<UsuarioResponse> listarUsuarios() {
		try {
			List<UsuarioEntity> lista = usuarioRepository.findAll();
			List<UsuarioResponse> response = new ArrayList<UsuarioResponse>();
			lista.removeIf(usuario -> usuario.getId().equals(1L));
			lista.forEach(usuario -> usuario
					.setData_nascimento(DateUtils.converterDataPorTexto(usuario.getData_nascimento())));
			lista.forEach(usuario -> response.add(new UsuarioResponse(usuario)));

			return response;
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na execução da listagem de usuários: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public UsuarioResponse buscarUsuarioPorId(Long id) {
		try {
			Optional<UsuarioEntity> usuOptional = usuarioRepository.findById(id);
			UsuarioEntity usuarioEntity;
			if (!usuOptional.isEmpty()) {
				usuarioEntity = usuOptional.get();
				UsuarioResponse response = new UsuarioResponse(usuarioEntity);
				return response;
			} else {
				throw new DefaultErrorException("Erro na execução da listagem de usuários",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na execução da listagem de usuários: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public void excluirUsuarioPorId(Long id) {
		try {
			Optional<UsuarioEntity> usOptional = usuarioRepository.findById(id);
			if (usOptional.isPresent()) {
				UsuarioEntity usuarioEntity = usOptional.get();
				
				usuarioRepository.delete(usuarioEntity);
			} else {
				throw new DefaultErrorException("Erro na execução da exclusão do usuário",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na execução da exclusão do usuário: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
