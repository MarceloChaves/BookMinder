package br.edu.fnr.bookminder.excecoes.livro;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class LivroDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = 1601377157139362699L;

	public LivroDuplicadoException(String message) {
		super(message);
	}
	
}
