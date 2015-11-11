package br.com.soaresdeandrade.advocacia.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	private String nome;
	@ElementCollection(targetClass=Permissoes.class)
    @Enumerated(EnumType.STRING)
	List<Permissoes> permissoes;

	public List<Permissoes> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissoes> permissoes) {
		this.permissoes = permissoes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}
}
