package br.edu.fnr.bookminder.excecoes.emprestimo;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class EmprestimoSemDataException extends RuntimeException {

	private static final long serialVersionUID = -8295168421936460806L;

	public EmprestimoSemDataException(String message) {
		super(message);
	}

	
	
}
