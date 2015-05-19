package br.edu.fnr.bookminder.excecoes.livro;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class LivroSemTituloException extends RuntimeException {

	private static final long serialVersionUID = -8427230795560361659L;

	public LivroSemTituloException(String message) {
		super(message);
	}

}
