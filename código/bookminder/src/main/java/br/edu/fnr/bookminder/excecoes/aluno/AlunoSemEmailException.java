package br.edu.fnr.bookminder.excecoes.aluno;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class AlunoSemEmailException extends RuntimeException {

	private static final long serialVersionUID = -6987581672991501741L;

	public AlunoSemEmailException(String message) {
		super(message);
	}

	
}
