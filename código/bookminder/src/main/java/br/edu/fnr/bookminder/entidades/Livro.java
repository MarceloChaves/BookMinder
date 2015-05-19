package br.edu.fnr.bookminder.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Livro {

	@Id
	private String codigo;
	private String titulo;
	private String autor;
	
	public Livro(){
		
	}
	
	
	public Livro(String titulo, String autor, String codigo) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.codigo = codigo;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public boolean equals(Object livro){
		
		String codigoObtido = ((Livro)livro).getCodigo();
		return codigoObtido.equals(this.codigo);
		
	}
	
}
