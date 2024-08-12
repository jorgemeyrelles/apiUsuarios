package br.com.cotiinformatica.services;

import org.springframework.web.bind.annotation.RequestBody;

import br.com.cotiinformatica.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.dtos.CriarUsuarioResponse;

public class UsuarioService {
	public CriarUsuarioResponse criar(@RequestBody CriarUsuarioRequest request) throws Exception {
		//TODO
		return null;
	}
	public AutenticarUsuarioResponse autenticar(@RequestBody AutenticarUsuarioRequest request) throws Exception {
		//TODO
		return null;
	}
}
