package br.edu.fnr.bookminder.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Notificacao {

	@Id
	@GeneratedValue
	String id;
	
	@OneToOne
	Emprestimo emprestimo;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	@Override
	public boolean equals(Object notificacao){
		
		String idObtida = ((Notificacao) notificacao).getId();
		return idObtida.equals(this.id);
		
	}
	
}
