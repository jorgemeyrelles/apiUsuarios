package br.com.cotiinformatica.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cotiinformatica.components.JwtTokenComponent;
import br.com.cotiinformatica.components.SHA256Component;
import br.com.cotiinformatica.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.dtos.ObterDadosUsuarioResponse;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.exceptions.AcessoNegadoException;
import br.com.cotiinformatica.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.repositories.PerfilRepository;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PerfilRepository perfilRepository;
	@Autowired
	private SHA256Component sha256Component;
	@Autowired
	private JwtTokenComponent jwtTokenComponent;

	public CriarUsuarioResponse criar(CriarUsuarioRequest request) throws Exception {

		if (usuarioRepository.findByEmail(request.getEmail()) != null)
			throw new EmailJaCadastradoException();

		// capturar os dados do usuário enviados na requisição (REQUEST)
		Usuario usuario = new Usuario();

		usuario.setId(UUID.randomUUID());
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setSenha(sha256Component.hash(request.getSenha()));
		usuario.setPerfil(perfilRepository.findPerfilByNome("DEFAULT"));

		// gravar o usuário no banco de dados
		usuarioRepository.save(usuario);

		// retornando os dados do usuário
		CriarUsuarioResponse response = new CriarUsuarioResponse();
		response.setId(usuario.getId());
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());
		response.setDataHoraCadastro(new Date());

		return response;
	}

	public AutenticarUsuarioResponse autenticar(@RequestBody AutenticarUsuarioRequest request) throws Exception {
		Usuario usuario = usuarioRepository.findByEmailAndSenha(request.getEmail(),
				sha256Component.hash(request.getSenha()));
		if (usuario == null) {
			throw new AcessoNegadoException();
		}

		AutenticarUsuarioResponse response = new AutenticarUsuarioResponse();
		response.setId(usuario.getId());
		response.setDataHoraAcesso(new Date());
		response.setEmail(usuario.getEmail());
		response.setNome(usuario.getNome());
		response.setNomePerfil(usuario.getPerfil().getNome());
		response.setDataHoraExpiracao(jwtTokenComponent.getExpirationDate());
		response.setTokenAcesso(jwtTokenComponent.generateToken(usuario));
		return response;
	}

	public ObterDadosUsuarioResponse obterDados(String token) throws Exception {
		String email = jwtTokenComponent.getEmailFromToken(token); // e-mail no corpo do token

		Usuario usuario = usuarioRepository.findByEmail(email); // verificando se o e-mail está no DB
		if (usuario == null) {
			throw new AcessoNegadoException();
		}

		ObterDadosUsuarioResponse response = new ObterDadosUsuarioResponse();
		response.setId(usuario.getId());
		response.setEmail(usuario.getEmail());
		response.setNome(usuario.getNome());
		response.setNomePerfil(usuario.getPerfil().getNome());

		return response;
	}
}
