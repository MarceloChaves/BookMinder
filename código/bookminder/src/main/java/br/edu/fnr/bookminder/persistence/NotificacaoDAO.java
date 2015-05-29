package br.edu.fnr.bookminder.persistence;

import br.edu.fnr.bookminder.entidades.Notificacao;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController	
public class NotificacaoDAO extends JPACrud<Notificacao, String> {
	private static final long serialVersionUID = -5180156214627219301L;

}
