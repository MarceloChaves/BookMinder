package br.edu.fnr.bookminder.persistence;

import br.edu.fnr.bookminder.entidades.Livro;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController	
public class LivroDAO extends JPACrud<Livro, String>{
	private static final long serialVersionUID = -6632791220774868029L;

}
