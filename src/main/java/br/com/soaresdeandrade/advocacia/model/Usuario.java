package br.com.soaresdeandrade.advocacia.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	@NotBlank(message="erro.usuario.nome.null")
	private String nome;
	@NotBlank(message="erro.usuario.email.null")
	@Email(message="erro.usuario.email.invalido")
	@Column(unique=true,nullable=false)
	private String email;
	@JsonIgnore
	@NotBlank(message="erro.usuario.senha")
	private String password;
	
	@ManyToMany
	 @JoinTable(name = "Usuario_Pefill", joinColumns = @JoinColumn(name = "usuarioID"), inverseJoinColumns = @JoinColumn(name = "perfilId",unique=false))
	private List<Perfil> perfis;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Perfil> getPerfis() {
		return perfis;
	}
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
}
