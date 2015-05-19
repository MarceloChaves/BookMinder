package br.edu.fnr.bookminder;

import java.util.ArrayList;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;

import br.edu.fnr.bookminder.business.FuncionarioBC;
import br.edu.fnr.bookminder.entidades.Funcionario;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioDuplicadoException;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioSemCpfException;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioSemNomeException;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioSemSenhaException;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@RunWith(DemoiselleRunner.class)
public class CadastroFuncionarioTeste {

	@Inject
	private Logger logger;
	@Inject
	private ResourceBundle bundle;
	@Inject
	private FuncionarioBC cadastro;
	@Inject
	private Funcionario funcionario;
	@Inject
	private Funcionario funcionarioDuplicado;
	@Inject
	private Funcionario funcionarioSemCPF;
	@Inject
	private Funcionario funcionarioSemNome;
	@Inject
	private Funcionario funcionarioSemSenha;

	@Before
	public void setUp(){

		funcionario.setCpf("100.926.524-50");
		funcionario.setNome("Duany");
		funcionario.setSenha("123");
		
		funcionarioDuplicado.setCpf("01");
		funcionarioDuplicado.setNome("Funcionario duplicado");
		funcionarioDuplicado.setSenha("123");
		
		funcionarioSemCPF.setNome("Funcionario sem CPF");
		funcionarioSemCPF.setSenha("123");

		funcionarioSemNome.setCpf("03");
		funcionarioSemNome.setSenha("123");

		funcionarioSemSenha.setCpf("04");
		funcionarioSemSenha.setNome("Funcionario sem senha");

	}
	/*@After
	public void tearDown(){

		ArrayList<Funcionario> cadastroVazio = cadastro.getCadastro();
		cadastroVazio.clear();
		cadastro.setCadastro(cadastroVazio);
	}*/

	@Test
	public void cadastrarFuncionarioComSucesso() {

		logger.info(bundle.getString("processo.inicio", "cadastrarFuncionarioComSucesso"));
		
		cadastro.cadastrar(funcionario);
		Assert.assertTrue(cadastro.estaCadastrado(funcionario));

		logger.info(bundle.getString("processo.fim", "cadastrarFuncionarioComSucesso"));
	}

	@Test(expected = FuncionarioDuplicadoException.class)
	public void falhaAoTentarCadastrarFuncionarioDuplicado(){

		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarFuncionarioDuplicado"));
		
		cadastro.cadastrar(funcionarioDuplicado);
		cadastro.cadastrar(funcionarioDuplicado);
		

	}

	@Test(expected = FuncionarioSemCpfException.class)
	public void falhaAoTentarCadastrarFuncionarioSemCPF(){

		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarFuncionarioSemCPF"));
		cadastro.cadastrar(funcionarioSemCPF);
		
	}

	@Test(expected = FuncionarioSemSenhaException.class)
	public void falhaAoTentarCadastrarFuncionarioSemSenha(){

		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarFuncionarioSemSenha"));
		cadastro.cadastrar(funcionarioSemSenha);
		

	}

	@Test(expected = FuncionarioSemNomeException.class)
	public void falharAoTentarCadastrarFuncionarioSemNome(){

		logger.info(bundle.getString("processo.inicio", "falharAoTentarCadastrarFuncionarioSemNome"));
		cadastro.cadastrar(funcionarioSemNome);
	

	}
}
