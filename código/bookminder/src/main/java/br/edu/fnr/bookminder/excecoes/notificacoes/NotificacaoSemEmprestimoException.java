package br.edu.fnr.bookminder.excecoes.notificacoes;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class NotificacaoSemEmprestimoException extends RuntimeException {

	private static final long serialVersionUID = -3937425477597930108L;

	public NotificacaoSemEmprestimoException(String arg0) {
		super(arg0);
	}
	
	

}
