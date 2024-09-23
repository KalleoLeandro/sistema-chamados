package br.com.srv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.srv.models.requests.UsuarioRequest;
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
		return ResponseEntity.noContent().build();
	} 

}
