package br.edu.fnr.bookminder.persistence;

import br.edu.fnr.bookminder.entidades.Emprestimo;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class EmprestimoDAO extends JPACrud<Emprestimo, String> {
	private static final long serialVersionUID = -3364188716642282484L;

}
