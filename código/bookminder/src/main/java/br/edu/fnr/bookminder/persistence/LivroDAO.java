package br.edu.fnr.bookminder.persistence;

import br.edu.fnr.bookminder.entidades.Livro;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController	
public class LivroDAO extends JPACrud<Livro, String>{

}
