package br.edu.fnr.bookminder.entidades;

import java.util.ArrayList;

public class Emprestimo {
	
	private String id;
	private ArrayList<Livro> livrosEmprestados = new ArrayList<Livro>();
	private Aluno aluno;
	private String dataEmprestimo;
	private String dataDevolução;
	
	
	public Emprestimo(){
		
	}

	public Emprestimo(String id) {
		this.id = id;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public ArrayList<Livro> getLivrosEmprestados() {
		return livrosEmprestados;
	}


	public void setLivrosEmprestados(ArrayList<Livro> livrosEmprestados) {
		this.livrosEmprestados = livrosEmprestados;
	}

	public void adicionarLivro(Livro livro){
		livrosEmprestados.add(livro);
		
	}

	public Aluno getAluno() {
		return aluno;
	}


	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}


	public String getDataEmprestimo() {
		return dataEmprestimo;
	}


	public void setDataEmprestimo(String dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}


	public String getDataDevolução() {
		return dataDevolução;
	}


	public void setDataDevolução(String dataDevolução) {
		this.dataDevolução = dataDevolução;
	}
	
	@Override
	public boolean equals(Object emprestimo){
		
		String idObtido = ((Emprestimo)emprestimo).getId();
		return idObtido.equals(this.id);
	}
}
