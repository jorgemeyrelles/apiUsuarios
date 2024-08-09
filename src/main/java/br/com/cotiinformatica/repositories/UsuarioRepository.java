package br.com.cotiinformatica.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	/*
	 * busca por email
	 */
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	Usuario findByEmail(@Param("email") String email);
	/*
	 * busca por email e senha
	 */
	@Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
	Usuario findByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);
	/*
	 * busca todos por perfil
	 */
	@Query("SELECT u FROM Usuario u INNER JOIN u.perfil p WHERE p.nome = :perfil")
	List<Usuario> findAllByPerfil(@Param("perfil") String perfil);
}
