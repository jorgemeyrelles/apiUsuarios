package br.com.cotiinformatica.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.dtos.CriarUsuarioResponse;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	@PostMapping("criar")
	public CriarUsuarioResponse criar(@RequestBody CriarUsuarioRequest request) throws Exception {
		return null;
	}
	
	@PostMapping("autenticar")
	public AutenticarUsuarioResponse autenticar(@RequestBody AutenticarUsuarioRequest request) throws Exception {
		//TODO
		return null;
	}
}
