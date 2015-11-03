package br.com.soaresdeandrade.advocacia.model.noticia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Noticia implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	  private Date data;
	  @Column(length=500)
	   private String texto;
	  @Column(length=100)
	   private String titulo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
