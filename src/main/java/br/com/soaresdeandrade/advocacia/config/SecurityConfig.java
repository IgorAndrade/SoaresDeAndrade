package br.com.soaresdeandrade.advocacia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import br.com.soaresdeandrade.advocacia.service.UserService;

@Configuration
@EnableWebMvcSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices("remember-me-key",
				userService());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("adm-ti").password("teste")
				.roles("ADM","ADM-TI").and().passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.eraseCredentials(true)
				.userDetailsService(userService())
				.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/favicon.ico", "/resources/**", "/signup").permitAll()
				.antMatchers("/teste").permitAll()
				.antMatchers("/adm").hasRole("adm")
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/signin").permitAll().
				failureUrl("/signin?error=1")
				.loginProcessingUrl("/authenticate").and().logout()
				.logoutUrl("/logout").permitAll()
				.logoutSuccessUrl("/signin?logout").and().rememberMe()
				.rememberMeServices(rememberMeServices())
				.key("remember-me-key");
	}
}