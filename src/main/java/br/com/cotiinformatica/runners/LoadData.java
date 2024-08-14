package br.com.cotiinformatica.runners;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.cotiinformatica.entities.Perfil;
import br.com.cotiinformatica.repositories.PerfilRepository;

@Component
public class LoadData implements ApplicationRunner {
	
	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Perfil perfil_default = new Perfil();
		perfil_default.setId(UUID.randomUUID());
		perfil_default.setNome("DEFAULT");
		
		Perfil perfil_admin = new Perfil();
		perfil_default.setId(UUID.randomUUID());
		perfil_default.setNome("ADMIN");
		
		if (perfilRepository.findPerfilByNome("DEFAULT") == null) {
			perfilRepository.save(perfil_default);
		}
		
		if (perfilRepository.findPerfilByNome("ADMIN") == null) {
			perfilRepository.save(perfil_admin);
		}
		
	}

}
