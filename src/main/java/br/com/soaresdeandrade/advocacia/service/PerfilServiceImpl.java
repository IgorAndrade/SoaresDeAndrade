package br.com.soaresdeandrade.advocacia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.soaresdeandrade.advocacia.error.RN;
import br.com.soaresdeandrade.advocacia.error.RNException;
import br.com.soaresdeandrade.advocacia.model.Perfil;
import br.com.soaresdeandrade.advocacia.model.Usuario;
import br.com.soaresdeandrade.advocacia.repository.PerfilRepository;
import br.com.soaresdeandrade.advocacia.repository.UsuarioRepository;

@Service
public class PerfilServiceImpl {

	@Autowired
	private PerfilRepository repository;
	
	public Perfil getPerfilById(Long id){
		Perfil perfil = repository.getOne(id);
//		int size = perfil.getPermissoes().size();
		return perfil;
		
	}
}
