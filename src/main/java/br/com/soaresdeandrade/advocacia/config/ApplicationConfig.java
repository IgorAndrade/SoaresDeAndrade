package br.com.soaresdeandrade.advocacia.config;

import static org.springframework.context.annotation.ComponentScan.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

import br.com.soaresdeandrade.advocacia.Application;

@Configuration
@PropertySource(value={"classpath:persistence${br.com.soaresdeandrade.modo}.properties","classpath:erros.properties"})
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @Filter({Controller.class, Configuration.class}))
class ApplicationConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
}