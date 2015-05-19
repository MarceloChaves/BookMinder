package br.edu.fnr.bookminder.excecoes.emprestimo;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class EmprestimoDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = -4639635014448321322L;

	public EmprestimoDuplicadoException(String message) {
		super(message);
	}


	
	
}
