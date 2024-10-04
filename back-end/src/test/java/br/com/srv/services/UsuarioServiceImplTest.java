package br.com.srv.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

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
import br.com.srv.services.impl.UsuarioServiceImpl;
import br.com.srv.utils.DateUtils;

@SpringBootTest
public class UsuarioServiceImplTest {

	@InjectMocks
	private UsuarioServiceImpl service;

	@Mock
	private UsuarioRepository usuarioRepository;

	@Mock
	private LoginRepository loginRepository;

	@Mock
	private EnderecoRepository enderecoRepository;

	@Mock
	private ContatoRepository contatoRepository;

	private UsuarioRequest request;

	private ContatoEntity contatoEntity;

	private EnderecoEntity enderecoEntity;

	private LoginEntity loginEntity;

	private UsuarioEntity entity;
	
	@Mock
	private DateUtils dateUtils;

	@BeforeEach
	public void setUp() {
		enderecoEntity = new EnderecoEntity(null, "Cep", "Rua", 100, null, "Bairro", "Cidade", "Uf", null);
		contatoEntity = new ContatoEntity(null, "Telefone", "Celular", "Email", null);
		loginEntity = new LoginEntity(null, "Login", "Senha", "Perfil", null);
		entity = new UsuarioEntity();
		entity.setNome("Teste");
		entity.setCpf("Cpf");
		entity.setData_nascimento("Data");
		entity.setSexo('M');
		entity.setLoginEntity(loginEntity);
		entity.setContatoEntity(contatoEntity);
		entity.setEnderecoEntity(enderecoEntity);
		request = new UsuarioRequest(null, "Teste", "Cpf", "Data", 'M', "Cep", "Rua", 100, null, "Bairro", "Cidade",
				"Uf", "Telefone", "Celular", "Email", "Login", "Senha", "Perfil");
	}

	@Test
	public void testGravarUsuarioOk() {

		Mockito.when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(entity);
		Mockito.when(loginRepository.save(any(LoginEntity.class))).thenReturn(loginEntity);
		Mockito.when(contatoRepository.save(any(ContatoEntity.class))).thenReturn(contatoEntity);
		Mockito.when(enderecoRepository.save(any(EnderecoEntity.class))).thenReturn(enderecoEntity);

		service.gravarUsuario(request);

		Mockito.verify(usuarioRepository, Mockito.times(1)).save(any(UsuarioEntity.class));
		Mockito.verify(loginRepository, Mockito.times(1)).save(any(LoginEntity.class));
		Mockito.verify(contatoRepository, Mockito.times(1)).save(any(ContatoEntity.class));
		Mockito.verify(enderecoRepository, Mockito.times(1)).save(any(EnderecoEntity.class));
	}

	@Test
	public void testGravarUsuarioAtualizadoOk() {

		request = new UsuarioRequest(1L, "Teste", "Cpf", "Data", 'M', "Cep", "Rua", 100, null, "Bairro", "Cidade", "Uf",
				"Telefone", "Celular", "Email", "Login", "Senha", "Perfil");

		Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(entity));

		Mockito.when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(entity);
		Mockito.when(loginRepository.save(any(LoginEntity.class))).thenReturn(loginEntity);
		Mockito.when(contatoRepository.save(any(ContatoEntity.class))).thenReturn(contatoEntity);
		Mockito.when(enderecoRepository.save(any(EnderecoEntity.class))).thenReturn(enderecoEntity);

		loginEntity.setId(1L);
		enderecoEntity.setId(1L);
		contatoEntity.setId(1L);

		service.gravarUsuario(request);

		Mockito.verify(usuarioRepository, Mockito.times(1)).save(any(UsuarioEntity.class));
		Mockito.verify(loginRepository, Mockito.times(1)).save(any(LoginEntity.class));
		Mockito.verify(contatoRepository, Mockito.times(1)).save(any(ContatoEntity.class));
		Mockito.verify(enderecoRepository, Mockito.times(1)).save(any(EnderecoEntity.class));
	}
	
	@Test
	public void testGravarUsuarioErroFind() {
		request = new UsuarioRequest(1L, "Teste", "Cpf", "Data", 'M', "Cep", "Rua", 100, null, "Bairro", "Cidade", "Uf",
				"Telefone", "Celular", "Email", "Login", "Senha", "Perfil");

		Mockito.when(usuarioRepository.findById(anyLong()))
				.thenReturn(Optional.empty());

		DefaultErrorException exception = Assertions.assertThrows(DefaultErrorException.class, () -> {
			service.gravarUsuario(request);
		});
		
		Assertions.assertTrue(exception.getMessage().contains("Erro"));
	}

	@Test
	public void testGravarUsuarioErro() {
		request = new UsuarioRequest(1L, "Teste", "Cpf", "Data", 'M', "Cep", "Rua", 100, null, "Bairro", "Cidade", "Uf",
				"Telefone", "Celular", "Email", "Login", "Senha", "Perfil");

		Mockito.when(usuarioRepository.findById(anyLong()))
				.thenThrow(new DefaultErrorException("Erro", HttpStatus.INTERNAL_SERVER_ERROR));

		DefaultErrorException exception = Assertions.assertThrows(DefaultErrorException.class, () -> {
			service.gravarUsuario(request);
		});
		
		Assertions.assertTrue(exception.getMessage().contains("Erro"));
	}
	
	@Test
	public void testListaUsuariosOk() {	    
	    List<UsuarioEntity> lista = new ArrayList<>();
	    entity.setId(2L);
	    lista.add(entity);
	    	    
	    Mockito.when(usuarioRepository.findAll()).thenReturn(lista);
	    	    
	    List<UsuarioResponse> response = service.listarUsuarios();		
	    
	    Mockito.verify(usuarioRepository, Mockito.times(1)).findAll();
	    

	    Assertions.assertFalse(response.isEmpty(), "A lista de usuários não deve estar vazia.");
	    Assertions.assertNotNull(response);
	}
	
	@Test
	public void testListaUsuariosErro() {	    
	    	    	    
		Mockito.when(usuarioRepository.findAll()).thenThrow(new DefaultErrorException("Erro", HttpStatus.INTERNAL_SERVER_ERROR));
    	
    	DefaultErrorException exception = Assertions.assertThrows(DefaultErrorException.class, () -> {
            service.listarUsuarios();
        });
	    	    
    	Assertions.assertTrue(exception.getMessage().contains("Erro"));
	}
	
	@Test
	public void testBuscarUsuarioPorIdOk() {
		Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(entity));
		UsuarioResponse response = service.buscarUsuarioPorId(1L);
		
		Assertions.assertNotNull(response);
	}
	
	@Test
	public void testBuscarUsuarioPorIdVazio() {
		
		Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
		DefaultErrorException exception = Assertions.assertThrows(DefaultErrorException.class, () -> {
            service.buscarUsuarioPorId(1L);
        });
		
    	Assertions.assertTrue(exception.getMessage().contains("Erro"));
	}
	
	@Test
	public void testBuscarUsuarioPorIdErro() {
		
		Mockito.when(usuarioRepository.findById(anyLong())).thenThrow(new DefaultErrorException("Erro", HttpStatus.INTERNAL_SERVER_ERROR));
		DefaultErrorException exception = Assertions.assertThrows(DefaultErrorException.class, () -> {
            service.buscarUsuarioPorId(1L);
        });
		
    	Assertions.assertTrue(exception.getMessage().contains("Erro"));
	}
	
	@Test
	public void testExcluirUsuarioPorIdOk() {
		
		Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(entity));
		
		Mockito.doNothing().when(usuarioRepository).delete(any());
		
		service.excluirUsuarioPorId(1L);
		
	    Mockito.verify(usuarioRepository, Mockito.times(1)).delete(entity);

	}
	
	@Test
	public void testExcluirUsuarioPorIdVazio() {
		
		Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		DefaultErrorException exception = Assertions.assertThrows(DefaultErrorException.class, () -> {
            service.excluirUsuarioPorId(1L);
        });
		
    	Assertions.assertTrue(exception.getMessage().contains("Erro"));
	}
	
	@Test
	public void testExcluirUsuarioPorIdErro() {
		
		Mockito.when(usuarioRepository.findById(anyLong())).thenThrow(new DefaultErrorException("Erro", HttpStatus.INTERNAL_SERVER_ERROR));
		
		DefaultErrorException exception = Assertions.assertThrows(DefaultErrorException.class, () -> {
            service.excluirUsuarioPorId(1L);
        });
		
    	Assertions.assertTrue(exception.getMessage().contains("Erro"));
	}
}
