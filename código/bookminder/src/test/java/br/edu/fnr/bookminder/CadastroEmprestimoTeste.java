package br.edu.fnr.bookminder;

import java.util.ArrayList;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import br.edu.fnr.bookminder.business.EmprestimoBC;
import br.edu.fnr.bookminder.entidades.Aluno;
import br.edu.fnr.bookminder.entidades.Emprestimo;
import br.edu.fnr.bookminder.entidades.Livro;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoDuplicadoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoLimiteExcedidoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemAlunoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemDataDevolucaoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemDataException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemIdException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemLivroException;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@RunWith(DemoiselleRunner.class)
public class CadastroEmprestimoTeste {

	@Inject
	private	ResourceBundle bundle;

	@Inject
	private Logger logger;

	@Inject 
	private EmprestimoBC cadastro;

	@Inject
	private Emprestimo emprestimo;

	@Inject
	private Emprestimo emprestimoSemId;

	@Inject
	private Emprestimo emprestimoSemLivro;

	@Inject
	private Emprestimo emprestimoSemAluno;

	@Inject 
	private Emprestimo emprestimoSemData;

	@Inject
	private Emprestimo emprestimoSemDataDevolucao;
	
	@Inject Emprestimo emprestimoLimiteExcedido;
	
	@Inject
	private Aluno aluno;

	@Inject
	private Livro livro;

	@Before
	public void setUp(){

		aluno.setNome("Duany");
		aluno.setMatricula("201110025");
		aluno.setEmail("email");

		livro.setTitulo("Paradigmas de Programação");

		emprestimo.setAluno(aluno);
		emprestimo.adicionarLivro(livro);
		emprestimo.setId("01");
		emprestimo.setDataEmprestimo("18/04/2015");
		emprestimo.setDataDevolução("24/04/2015");

		emprestimoSemAluno.setId("02");
		emprestimoSemAluno.adicionarLivro(livro);
		emprestimoSemAluno.setDataEmprestimo("18/04/2015");
		emprestimoSemAluno.setDataDevolução("24/04/2015");

		emprestimoSemLivro.setAluno(aluno);
		emprestimoSemLivro.setId("03");
		emprestimoSemLivro.setDataEmprestimo("18/04/2015");
		emprestimoSemLivro.setDataDevolução("24/04/2015");


		emprestimoSemId.setAluno(aluno);
		emprestimoSemId.adicionarLivro(livro);
		emprestimoSemId.setDataEmprestimo("18/04/2015");
		emprestimoSemId.setDataDevolução("24/04/2015");

		emprestimoSemData.setAluno(aluno);
		emprestimoSemData.adicionarLivro(livro);
		emprestimoSemData.setId("05");
		emprestimoSemData.setDataDevolução("24/04/2015");

		emprestimoSemDataDevolucao.setAluno(aluno);
		emprestimoSemDataDevolucao.setId("06");
		emprestimoSemDataDevolucao.adicionarLivro(livro);
		emprestimoSemDataDevolucao.setDataEmprestimo("18/04/2015");
		
		emprestimoLimiteExcedido.setAluno(aluno);
		emprestimoLimiteExcedido.setId("07");
		emprestimoLimiteExcedido.setDataEmprestimo("18/04/2015");
		emprestimoLimiteExcedido.setDataDevolução("24/04/2015");
	}

	/*@After
	public void tearDown(){
		
		
		ArrayList<Livro> livrosEmprestados = emprestimo.getLivrosEmprestados();
		livrosEmprestados.clear();
		emprestimo.setLivrosEmprestados(livrosEmprestados);
		
		
		ArrayList<Emprestimo> cadastroVazio = cadastro.getCadastro();
		cadastroVazio.clear();
		cadastro.setCadastro(cadastroVazio);
	}
*/
	@Test
	public void cadastrarEmprestimoComSucesso(){

		logger.info(bundle.getString("processo.inicio", "cadastrarEmprestimoComSucesso"));

		System.out.println(emprestimo.getLivrosEmprestados().size());
		cadastro.cadastrar(emprestimo);
		
		logger.info(bundle.getString("processo.fim", "cadastrarEmprestimoComSucesso"));
		Assert.assertTrue(cadastro.estaCadastrado(emprestimo));

		
	}

	@Test (expected = EmprestimoDuplicadoException.class)
	public void falhaAoTentarCadastrarEmprestimoDuplicado(){

		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarEmprestimoDuplicado"));
		cadastro.cadastrar(emprestimo);
		cadastro.cadastrar(emprestimo);
	}

	@Test (expected = EmprestimoSemIdException.class)
	public void falhaAoTentarCadastrarEmprestimoSemId(){

		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarEmprestimoSemId"));
		cadastro.cadastrar(emprestimoSemId);
	}

	@Test (expected = EmprestimoSemLivroException.class)
	public void falhaAoTentarCadastrarEmprestimoSemLivro(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarEmprestimoSemLivro"));
		cadastro.cadastrar(emprestimoSemLivro);
	}
	
	@Test (expected = EmprestimoSemAlunoException.class)
	public void falharAoTentarCadastrarEmprestimoSemAluno(){
		
		logger.info(bundle.getString("processo.inicio", "falharAoTentarCadastrarEmprestimoSemAluno"));
		cadastro.cadastrar(emprestimoSemAluno);
	}
	
	@Test (expected = EmprestimoSemDataException.class)
	public void falhaAoTentarCadastrarEmprestimoSemData(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarEmprestimoSemData"));
		cadastro.cadastrar(emprestimoSemData);
	}
	
	@Test (expected = EmprestimoSemDataDevolucaoException.class)
	public void falhaAoTentarCadastrarEmprestimoSemDataDevolucao(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarEmprestimoSemDataDevolucao"));
		cadastro.cadastrar(emprestimoSemDataDevolucao);
	}

	@Test (expected = EmprestimoLimiteExcedidoException.class)
	public void falhaAoTentarCadastrarEmprestimoComLimiteExcedido(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarEmprestimoComLimiteExcedido"));

		livro.setCodigo("01");
		emprestimoLimiteExcedido.adicionarLivro(livro);
		
		livro.setCodigo("02");
		emprestimoLimiteExcedido.adicionarLivro(livro);
		
		livro.setCodigo("03");
		emprestimoLimiteExcedido.adicionarLivro(livro);
		
		livro.setCodigo("04");
		
		emprestimoLimiteExcedido.adicionarLivro(livro);
		livro.setCodigo("05");
		
		cadastro.cadastrar(emprestimoLimiteExcedido);
	}

}
