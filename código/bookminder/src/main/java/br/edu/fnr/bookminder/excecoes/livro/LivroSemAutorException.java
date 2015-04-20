package br.edu.fnr.bookminder.excecoes.livro;

public class LivroSemAutorException extends RuntimeException {

	private static final long serialVersionUID = 7750703367488401685L;

	public LivroSemAutorException(String message) {
		super(message);
	}

}
