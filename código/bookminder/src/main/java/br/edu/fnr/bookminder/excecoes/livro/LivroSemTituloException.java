package br.edu.fnr.bookminder.excecoes.livro;

public class LivroSemTituloException extends RuntimeException {

	private static final long serialVersionUID = -8427230795560361659L;

	public LivroSemTituloException(String message) {
		super(message);
	}

}
