package br.edu.fnr.bookminder.excecoes.funcionario;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class FuncionarioSemNomeException extends RuntimeException {

	private static final long serialVersionUID = -6610637307160460496L;

	public FuncionarioSemNomeException(String msg) {
		super(msg);
	}

	
	
}
