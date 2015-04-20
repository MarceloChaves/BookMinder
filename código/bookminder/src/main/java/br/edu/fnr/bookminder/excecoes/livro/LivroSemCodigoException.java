package br.edu.fnr.bookminder.excecoes.livro;

public class LivroSemCodigoException extends RuntimeException {

	private static final long serialVersionUID = -7582669521017701635L;

	public LivroSemCodigoException(String message) {
		super(message);
	}
	
}
