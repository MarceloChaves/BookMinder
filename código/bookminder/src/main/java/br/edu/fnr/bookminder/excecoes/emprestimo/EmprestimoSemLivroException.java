package br.edu.fnr.bookminder.excecoes.emprestimo;

public class EmprestimoSemLivroException extends RuntimeException {
	
	private static final long serialVersionUID = -5713829140461156645L;

	public EmprestimoSemLivroException(String message) {
		super(message);
	}

	
}
