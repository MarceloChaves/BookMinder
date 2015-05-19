package br.edu.fnr.bookminder.excecoes.emprestimo;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class EmprestimoSemIdException extends RuntimeException {

	private static final long serialVersionUID = -8709648308803427030L;

	public EmprestimoSemIdException(String message) {
		super(message);
	}

	

	
}
