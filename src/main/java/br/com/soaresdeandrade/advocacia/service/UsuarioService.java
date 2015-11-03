package br.com.soaresdeandrade.advocacia.service;

import java.util.List;

import br.com.soaresdeandrade.advocacia.model.usuario.Usuario;

public interface UsuarioService {

	public Usuario findByEmail(String email);
	public Usuario findById(Long id);
	public List<Usuario> ListarTodos();
	public Long salvar(Usuario usuario)throws Exception;
	public void remover(Usuario usuario);
	public void remover(Long id);
	
	
	
}
