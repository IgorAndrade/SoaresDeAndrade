package br.com.soaresdeandrade.advocacia.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.soaresdeandrade.advocacia.model.Perfil;
import br.com.soaresdeandrade.advocacia.model.Permissoes;
import br.com.soaresdeandrade.advocacia.repository.PerfilRepository;
import br.com.soaresdeandrade.advocacia.support.web.MessageHelper;


@Controller
@RequestMapping(value = "/perfil")
public class PerfilController {
	private static final String LISTA_PERFIS = "cadastro/lista_perfis";
	private static final String PERFIL = "cadastro/perfil";
	@Autowired
	private PerfilRepository repository;
	
	
	@RequestMapping(value = {"","/","/listar"}, method = RequestMethod.GET)
	public ModelAndView listar(Principal principal) {
		ModelAndView modelAndView = new ModelAndView(LISTA_PERFIS);
		List<Perfil> listarTodos = repository.findAll();
		modelAndView.addObject("lista", listarTodos);
		return modelAndView;
	}
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(Principal principal) {
		ModelAndView modelAndView = new ModelAndView(PERFIL);
		modelAndView.addObject("perfil", new Perfil());
		Permissoes[] permissoes = Permissoes.values();
		modelAndView.addObject("permissoes", permissoes);
		return modelAndView;
	}
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(Principal principal,@Valid @ModelAttribute Perfil perfil,Errors erros,RedirectAttributes ra,Model modelo) {
		Permissoes[] permissoes = Permissoes.values();
		modelo.addAttribute("permissoes", permissoes);
		if(erros.hasErrors()){
			MessageHelper.addErrorAttribute(modelo, "erro.cadastro.geral",erros.getErrorCount());
			return PERFIL;
		}
		try {
			repository.save(perfil);
			MessageHelper.addSuccessAttribute(ra, "form.cadastro.sucesso", perfil.getNome());
			return "redirect:/perfil";
		} catch (Exception e) {
			MessageHelper.addErrorAttribute(modelo, "erro.cadastro.geral",1);
			return PERFIL;
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") Long id){
		ModelAndView modelAndView = new ModelAndView(PERFIL);
		Perfil perfil = repository.getOne(id);
		modelAndView.addObject("perfil", perfil);
		Permissoes[] permissoes = Permissoes.values();
		modelAndView.addObject("permissoes", permissoes);
		return modelAndView;
	}
	
}
