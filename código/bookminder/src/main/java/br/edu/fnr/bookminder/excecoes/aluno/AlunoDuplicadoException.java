package br.edu.fnr.bookminder.excecoes.aluno;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class AlunoDuplicadoException extends RuntimeException {
	
	private static final long serialVersionUID = -6063581725319170428L;

	public AlunoDuplicadoException(String message) {
		super(message);
	}
	
	

}
