package br.com.soaresdeandrade.advocacia.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.soaresdeandrade.advocacia.error.RN;
import br.com.soaresdeandrade.advocacia.error.RNException;
import br.com.soaresdeandrade.advocacia.model.Usuario;
import br.com.soaresdeandrade.advocacia.service.UsuarioService;
import br.com.soaresdeandrade.advocacia.support.web.MessageHelper;


@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	private static final String LISTA_USUARIOS = "cadastro/lista_usuarios";
	private static final String USUARIO = "cadastro/usuario";
	@Autowired
	private UsuarioService service;
	
	
	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView listar(Principal principal) {
		ModelAndView modelAndView = new ModelAndView(LISTA_USUARIOS);
		List<Usuario> listarTodos = service.ListarTodos();
		modelAndView.addObject("lista", listarTodos);
		return modelAndView;
	}
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(Principal principal) {
		ModelAndView modelAndView = new ModelAndView(USUARIO);
		modelAndView.addObject("usuario", new Usuario());
		return modelAndView;
	}
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String novo(Principal principal,@Valid @ModelAttribute Usuario usuario,Errors erros,RedirectAttributes ra,Model modelo) {
		if(erros.hasErrors()){
			MessageHelper.addErrorAttribute(modelo, "erro.cadastro.geral",erros.getErrorCount());
			return USUARIO;
		}
		try {
			service.salvar(usuario);
			MessageHelper.addSuccessAttribute(ra, "form.cadastro.usuario.sucesso", usuario.getNome(),usuario.getEmail());
			return "redirect:/usuario";
		} catch (RNException e) {
			for(RN rn: e.getRn()){
				MessageHelper.addErrorAttribute(modelo, rn.getMsg());
			}
			return USUARIO;
		}
	}
}
