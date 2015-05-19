package br.edu.fnr.bookminder.excecoes.aluno;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class AlunoSemNomeException extends RuntimeException {

	private static final long serialVersionUID = -9033496576142435154L;

	public AlunoSemNomeException(String message) {
		super(message);
	}
	
	

}
