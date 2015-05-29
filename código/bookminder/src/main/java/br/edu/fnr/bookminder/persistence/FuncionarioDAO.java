package br.edu.fnr.bookminder.persistence;

import br.edu.fnr.bookminder.entidades.Funcionario;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class FuncionarioDAO extends JPACrud<Funcionario, String>{
	private static final long serialVersionUID = 3328273966325571908L;

}
