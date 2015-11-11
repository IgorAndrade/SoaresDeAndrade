package br.com.soaresdeandrade.advocacia.config;

import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

import br.com.soaresdeandrade.advocacia.Application;

@Configuration
@PropertySource(value = {
		"classpath:persistence.properties",
		"classpath:erros.properties" })
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @Filter({
		Controller.class, Configuration.class }))
class ApplicationConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public Validator getValidador() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}

}