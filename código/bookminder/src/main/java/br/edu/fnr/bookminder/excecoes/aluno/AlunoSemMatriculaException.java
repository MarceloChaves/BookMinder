package br.edu.fnr.bookminder.excecoes.aluno;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class AlunoSemMatriculaException extends RuntimeException {

	private static final long serialVersionUID = 9142414225287115357L;

	public AlunoSemMatriculaException(String message) {
		super(message);
	}
	
}
