package br.com.soaresdeandrade.advocacia.signin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.soaresdeandrade.advocacia.support.web.MessageHelper;

@Controller
public class SigninController {

	@RequestMapping(value = "signin")
	public String signin(@RequestParam(value="error",required=false) boolean isErro, Model model) {
		if(isErro)
		 MessageHelper.addErrorAttribute(model, "erro.usuario.naoencontrado");
        return "signin/signin";
    }
	@RequestMapping(value = "/logout")
	public String logout( HttpServletRequest request, HttpServletResponse response) {
		 CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
		    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		    cookieClearingLogoutHandler.logout(request, response, null);
		    securityContextLogoutHandler.logout(request, response, null);
		return "signin/signin";
	}
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String erroAcesso(RedirectAttributes ra) {
		 MessageHelper.addErrorAttribute(ra, "erro.acessoNegado");
		 return "redirect:/";
    }
}
