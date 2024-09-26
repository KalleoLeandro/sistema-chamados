package br.com.srv.services;

import java.util.List;

import br.com.srv.models.requests.UsuarioRequest;
import br.com.srv.models.responses.UsuarioResponse;

public interface UsuarioService {
	
	public void gravarUsuario(UsuarioRequest usuarioRequest);

	public List<UsuarioResponse> listarUsuarios(); 
	
	public UsuarioResponse buscarUsuarioPorId(Long id);
	
	public void excluirUsuarioPorId(Long id);
}
