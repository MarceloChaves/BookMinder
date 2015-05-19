package br.edu.fnr.bookminder.persistence;

import br.edu.fnr.bookminder.entidades.Aluno;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController	
public class AlunoDAO extends JPACrud<Aluno, String>{

}
