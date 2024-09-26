package br.com.srv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.srv.models.requests.UsuarioRequest;
import br.com.srv.models.responses.UsuarioResponse;
import br.com.srv.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/gravar-usuario")
	public ResponseEntity<Void> gravarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest){
		usuarioService.gravarUsuario(usuarioRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/listar-usuarios")
	public ResponseEntity<List<UsuarioResponse>> listarUsuarios(){		
		return ResponseEntity.ok(usuarioService.listarUsuarios());
	}
	
	@GetMapping("/buscar-por-id/{id}")	
	public ResponseEntity<UsuarioResponse> buscarUsuarioPorId(@PathVariable Long id){		
		return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
	} 
	
	@DeleteMapping("/excluir-usuario-por-id/{id}")
	public ResponseEntity<Void> excluirUsuarioPorId(@PathVariable Long id){	
		usuarioService.excluirUsuarioPorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}  

}
