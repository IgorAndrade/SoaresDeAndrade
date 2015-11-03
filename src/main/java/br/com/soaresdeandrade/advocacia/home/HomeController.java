package br.com.soaresdeandrade.advocacia.home;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.soaresdeandrade.advocacia.repository.NoticiaRepository;

@Controller
public class HomeController {
	@Autowired
	NoticiaRepository noticiaRepository;
	private final PageRequest request =
            new PageRequest(2, 2, Sort.Direction.DESC, "data");
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
		return principal != null ? "home/home" : "signin/signin";
	}
}
