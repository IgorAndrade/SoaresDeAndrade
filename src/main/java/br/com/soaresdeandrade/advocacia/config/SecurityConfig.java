package br.com.soaresdeandrade.advocacia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import br.com.soaresdeandrade.advocacia.service.LoginService;

@Configuration
@EnableWebMvcSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("login")
	private LoginService loginSevice;
	

	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices("remember-me-key",
				loginSevice);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("adm-ti").password("s3nh4")
		.authorities("ADM","ADM-TI","CAD");
		auth.eraseCredentials(true)
				.userDetailsService(loginSevice)
				.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/favicon.ico", "/resources/**", "/signup").permitAll()
				.antMatchers("/teste").permitAll()
				.antMatchers("/usuario","/usuario/*").hasAnyAuthority("cad_user","CAD","ADM-TI")
				.antMatchers("/adm").hasRole("adm")
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/signin").permitAll().
				failureUrl("/signin?error=true")
				.loginProcessingUrl("/authenticate").and().logout()
				.logoutUrl("/logout").invalidateHttpSession(true).permitAll()
				.logoutSuccessUrl("/signin?logout").and().rememberMe()
				.rememberMeServices(rememberMeServices())
				.key("remember-me-key").and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
}