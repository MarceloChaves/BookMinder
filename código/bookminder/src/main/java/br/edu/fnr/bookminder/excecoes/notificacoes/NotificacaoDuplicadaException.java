package br.edu.fnr.bookminder.excecoes.notificacoes;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class NotificacaoDuplicadaException extends RuntimeException {


	private static final long serialVersionUID = -310492926860175285L;

	public NotificacaoDuplicadaException(String arg0) {
		super(arg0);
	}
	
	

}
