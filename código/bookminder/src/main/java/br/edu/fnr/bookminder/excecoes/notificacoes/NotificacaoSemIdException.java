package br.edu.fnr.bookminder.excecoes.notificacoes;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class NotificacaoSemIdException extends RuntimeException {

	
	private static final long serialVersionUID = -7767144925099572371L;

	public NotificacaoSemIdException(String arg0) {
		super(arg0);
	}
	
	

}
