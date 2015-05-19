package br.edu.fnr.bookminder.excecoes.emprestimo;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class EmprestimoLimiteExcedidoException extends RuntimeException {

	
	private static final long serialVersionUID = -4972046155723665796L;

	public EmprestimoLimiteExcedidoException(String arg0) {
		super(arg0);
	}
}
