package br.edu.fnr.bookminder.excecoes.aluno;

public class AlunoSemMatriculaException extends RuntimeException {

	private static final long serialVersionUID = 9142414225287115357L;

	public AlunoSemMatriculaException(String message) {
		super(message);
	}
	
}
