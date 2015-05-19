package br.edu.fnr.bookminder.excecoes.funcionario;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class FuncionarioDuplicadoException extends RuntimeException {

	
	private static final long serialVersionUID = 7579508168158788000L;

	public FuncionarioDuplicadoException(String msg){
		super(msg);
	
	}

	
	
}
