package br.edu.fnr.bookminder.excecoes.emprestimo;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class EmprestimoSemAlunoException extends RuntimeException {
	
	private static final long serialVersionUID = -609320926775990774L;

	public EmprestimoSemAlunoException(String message) {
		super(message);
	}

	
	
}
