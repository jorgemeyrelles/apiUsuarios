package br.com.cotiinformatica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
	/*
	 * busca por nome
	 */
	@Query("SELECT p FROM Perfil p WHERE p.nome = :nome")
	Perfil findPerfilByNome(@Param("nome") String nome);
}
