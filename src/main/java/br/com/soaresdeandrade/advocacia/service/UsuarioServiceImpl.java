package br.com.soaresdeandrade.advocacia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.soaresdeandrade.advocacia.error.RN;
import br.com.soaresdeandrade.advocacia.error.RNException;
import br.com.soaresdeandrade.advocacia.model.Usuario;
import br.com.soaresdeandrade.advocacia.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private Validator validator;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Usuario findById(Long id) {
		return repository.findOne(id);
	}

	@Override
	@Secured("cad_user")
	public List<Usuario> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public Long salvar(Usuario usuario) throws RNException {

		Set<ConstraintViolation<Usuario>> validate = validator
				.validate(usuario);
		if (!validate.isEmpty()) {
			List<RN> rns = new ArrayList<RN>();
			for (ConstraintViolation<Usuario> erro : validate) {
				rns.add(RN.valueOfMsg(erro.getMessage()));
			}
			throw new RNException(rns.toArray(new RN[rns.size()]));

		}
		try {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			return repository.save(usuario).getId();

		} catch (Exception ex) {
			throw new RNException(RN.USER_REPETIDO);
		}
	}

	@Override
	public void remover(Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub

	}

	public void setRepository(UsuarioRepository repository) {
		this.repository = repository;
	}
}
