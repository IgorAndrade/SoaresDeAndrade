package br.com.soaresdeandrade.advocacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.soaresdeandrade.advocacia.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Usuario findByEmail(String email);
}
