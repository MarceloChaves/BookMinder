package br.edu.fnr.bookminder.excecoes.funcionario;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class FuncionarioSemSenhaException extends RuntimeException {

	private static final long serialVersionUID = 5990052729460763281L;

	public FuncionarioSemSenhaException(String msg) {
		super(msg);
	}

	
	
}
