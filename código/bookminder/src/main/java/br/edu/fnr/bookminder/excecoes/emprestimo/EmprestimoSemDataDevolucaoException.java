package br.edu.fnr.bookminder.excecoes.emprestimo;

public class EmprestimoSemDataDevolucaoException extends RuntimeException {

	private static final long serialVersionUID = 2022445967165109131L;

	public EmprestimoSemDataDevolucaoException(String message) {
		super(message);
	}

}


