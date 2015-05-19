package br.edu.fnr.bookminder.config;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration (resource = "cadastro")
public class CadastroEmprestimoConfig {
	
	@Name ("limite.Emprestimos")
	private int limiteEmprestimos;

	public int getLimiteEmprestimos() {
		return limiteEmprestimos;
	}
	
}
