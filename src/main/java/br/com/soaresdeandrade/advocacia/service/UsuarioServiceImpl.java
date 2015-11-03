package br.com.soaresdeandrade.advocacia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import br.com.soaresdeandrade.advocacia.model.usuario.Usuario;
import br.com.soaresdeandrade.advocacia.repository.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	@Value("#{erro.usuario.email.null}")
	private String erroEmailObrigatorio;
	
	@Override
	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Usuario findById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Usuario> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public Long salvar(Usuario usuario) throws Exception {
		if(StringUtils.isEmpty(usuario.getEmail()))
			throw new Exception("Email não valido");
		return repository.save(usuario).getId();
	}

	@Override
	public void remover(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub
		
	}

}
