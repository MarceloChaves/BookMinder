package br.edu.fnr.bookminder.excecoes.funcionario;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class FuncionarioSemCpfException extends RuntimeException {

	private static final long serialVersionUID = -9207650457316452077L;

	public FuncionarioSemCpfException(String msg) {
		super(msg);
	}

}
