package br.edu.fnr.bookminder.excecoes.livro;

public class LivroDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = 1601377157139362699L;

	public LivroDuplicadoException(String message) {
		super(message);
	}
	
}
