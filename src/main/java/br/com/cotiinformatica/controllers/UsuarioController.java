package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.dtos.ObterDadosUsuarioResponse;
import br.com.cotiinformatica.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("criar")
	public CriarUsuarioResponse criar(@RequestBody @Valid CriarUsuarioRequest request) throws Exception {
		return usuarioService.criar(request);
	}

	@PostMapping("autenticar")
	public AutenticarUsuarioResponse autenticar(@RequestBody @Valid AutenticarUsuarioRequest request) throws Exception {
		return usuarioService.autenticar(request);
	}
	@PostMapping("obter-dados")
	public ObterDadosUsuarioResponse obterDados(HttpServletRequest request) throws Exception {
		String token = request.getHeader("Authorization").replace("Bearer", "").trim();
		return usuarioService.obterDados(token);
	}
}
