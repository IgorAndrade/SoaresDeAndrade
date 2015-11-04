package br.com.soaresdeandrade.advocacia.usuario;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import br.com.soaresdeandrade.advocacia.Application;
import br.com.soaresdeandrade.advocacia.error.RN;
import br.com.soaresdeandrade.advocacia.error.RNException;
import br.com.soaresdeandrade.advocacia.model.usuario.Usuario;
import br.com.soaresdeandrade.advocacia.repository.UsuarioRepository;
import br.com.soaresdeandrade.advocacia.service.UsuarioService;
import br.com.soaresdeandrade.advocacia.service.UsuarioServiceImpl;
@RunWith(MockitoJUnitRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @Filter({
	Controller.class, Configuration.class }))
public class UsuarioServiceTest {
	
	@InjectMocks
	private UsuarioService service = new UsuarioServiceImpl();
	@Mock
	private UsuarioRepository repositoryMock;
	@Spy
	Validator validator=Validation.buildDefaultValidatorFactory().getValidator();
	@Value("${erro.usuario.email.null}")
	private String erroEmailNull;
	

	@Test
	public void usuarioEmailNull() {
		Usuario usuario = new Usuario();
		usuario.setNome("teste");
		try{
			service.salvar(usuario);
			fail("Não lançou exceção");
		}catch(RNException rne){
			verify(repositoryMock, never()).save(usuario);
			assertThat(rne.getRn(), hasItems(RN.EMAIL_NULL));
		}
		catch (Exception e) {
			fail("Exceção errada");
		}
	}

}
