package br.edu.fnr.bookminder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import br.edu.fnr.bookminder.business.AlunoBC;
import br.edu.fnr.bookminder.business.EmprestimoBC;
import br.edu.fnr.bookminder.business.LivroBC;
import br.edu.fnr.bookminder.entidades.Aluno;
import br.edu.fnr.bookminder.entidades.Emprestimo;
import br.edu.fnr.bookminder.entidades.Livro;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoDuplicadoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoLimiteExcedidoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemAlunoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemDataDevolucaoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemDataException;
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
	private EmprestimoBC emprestimoBC;

	@Inject
	private Emprestimo emprestimo;

	@Inject
	private Emprestimo emprestimoSemLivro;

	@Inject
	private Emprestimo emprestimoSemAluno;

	@Inject 
	private Emprestimo emprestimoSemData;

	@Inject
	private Emprestimo emprestimoSemDataDevolucao;
	
	@Inject 
	Emprestimo emprestimoLimiteExcedido;
	
	@Inject
	private Aluno aluno;

	@Inject
	private Livro livro;
	
	@Inject
	private Livro livro2;
	
	@Inject
	private Livro livro3;
	
	@Inject
	private Livro livro4;
	
	@Inject
	private Livro livro5;
	
	@Inject
	LivroBC livroBC;
	
	@Inject
	AlunoBC alunoBC;

	@Before
	public void setUp(){

		aluno.setNome("Duany");
		aluno.setMatricula("201110025");
		aluno.setEmail("email");
		
		alunoBC.cadastrar(aluno);
		
		livro.setTitulo("The Lord Of The Rings - The Two Towers");
		livro.setAutor("JRR Tolkien");
		livro.setCodigo("TLOR2T");
		
		livro2.setTitulo("The Lord Of The Rings - The Two Towers");
		livro2.setAutor("JRR Tolkien");
		livro2.setCodigo("02");

		livro3.setTitulo("The Lord Of The Rings - The Two Towers");
		livro3.setAutor("JRR Tolkien");
		livro3.setCodigo("03");

		livro4.setTitulo("The Lord Of The Rings - The Two Towers");
		livro4.setAutor("JRR Tolkien");
		livro4.setCodigo("04");
		

		livro5.setTitulo("The Lord Of The Rings - The Two Towers");
		livro5.setAutor("JRR Tolkien");
		livro5.setCodigo("05");
		

		livro2.setTitulo("The Lord Of The Rings - The Two Towers");
		livro2.setAutor("JRR Tolkien");
		livro2.setCodigo("02");
		
		livroBC.cadastrar(livro);
		livroBC.cadastrar(livro2);
		livroBC.cadastrar(livro3);
		livroBC.cadastrar(livro4);
		livroBC.cadastrar(livro5);
		
		emprestimo.setAluno(aluno);
		emprestimo.adicionarLivro(livro);
		emprestimo.setDataEmprestimo("18/04/2015");
		emprestimo.setDataDevolução("24/04/2015");

		emprestimoSemAluno.adicionarLivro(livro);
		emprestimoSemAluno.setDataEmprestimo("18/04/2015");
		emprestimoSemAluno.setDataDevolução("24/04/2015");

		emprestimoSemLivro.setAluno(aluno);
		emprestimoSemLivro.setDataEmprestimo("18/04/2015");
		emprestimoSemLivro.setDataDevolução("24/04/2015");

		emprestimoSemData.setAluno(aluno);
		emprestimoSemData.adicionarLivro(livro);
		emprestimoSemData.setDataDevolução("24/04/2015");

		emprestimoSemDataDevolucao.setAluno(aluno);
		emprestimoSemDataDevolucao.adicionarLivro(livro);
		emprestimoSemDataDevolucao.setDataEmprestimo("18/04/2015");
		
		emprestimoLimiteExcedido.setAluno(aluno);
		emprestimoLimiteExcedido.setDataEmprestimo("18/04/2015");
		emprestimoLimiteExcedido.setDataDevolução("24/04/2015");
	}

	@After
	public void tearDown(){
		
		List<Emprestimo> emprestimosCadastrados = emprestimoBC.findAll();
		List<String> emprestimoIds = new ArrayList<String>();
	
		List<Aluno> alunosCadastrados = alunoBC.findAll();
		List<String> alunoIds = new ArrayList<String>();
		

		List<Livro> livrosCadastrados = livroBC.findAll();
		List<String> livroIds = new ArrayList<String>();
		
		
		for(Aluno aluno : alunosCadastrados){
			alunoIds.add(aluno.getMatricula());
		}
		
	
	
		for(Emprestimo emprestimo : emprestimosCadastrados){
			emprestimoIds.add(emprestimo.getId());
		}
		
		
		
		for(Livro livro : livrosCadastrados){
			livroIds.add(livro.getCodigo());
		}
		
		emprestimoBC.delete(emprestimoIds);
		livroBC.delete(livroIds);
		alunoBC.delete(alunoIds);
	}

	@Test
	public void cadastrarEmprestimoComSucesso(){

		logger.info(bundle.getString("processo.inicio", "cadastrarEmprestimoComSucesso"));

		System.out.println(emprestimo.getLivrosEmprestados().size());
		emprestimoBC.cadastrar(emprestimo);
		
		logger.info(bundle.getString("processo.fim", "cadastrarEmprestimoComSucesso"));
		Assert.assertTrue(emprestimoBC.estaCadastrado(emprestimo));

		
	}

	@Test (expected = EmprestimoDuplicadoException.class)
	public void falhaAoTentarCadastrarEmprestimoDuplicado(){

		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarEmprestimoDuplicado"));
		emprestimoBC.cadastrar(emprestimo);
		emprestimoBC.cadastrar(emprestimo);
	}

	
	@Test (expected = EmprestimoSemLivroException.class)
	public void falhaAoTentarCadastrarEmprestimoSemLivro(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarEmprestimoSemLivro"));
		emprestimoBC.cadastrar(emprestimoSemLivro);
	}
	
	@Test (expected = EmprestimoSemAlunoException.class)
	public void falharAoTentarCadastrarEmprestimoSemAluno(){
		
		logger.info(bundle.getString("processo.inicio", "falharAoTentarCadastrarEmprestimoSemAluno"));
		emprestimoBC.cadastrar(emprestimoSemAluno);
	}
	
	@Test (expected = EmprestimoSemDataException.class)
	public void falhaAoTentarCadastrarEmprestimoSemData(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarEmprestimoSemData"));
		emprestimoBC.cadastrar(emprestimoSemData);
	}
	
	@Test (expected = EmprestimoSemDataDevolucaoException.class)
	public void falhaAoTentarCadastrarEmprestimoSemDataDevolucao(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarEmprestimoSemDataDevolucao"));
		emprestimoBC.cadastrar(emprestimoSemDataDevolucao);
	}

	@Test (expected = EmprestimoLimiteExcedidoException.class)
	public void falhaAoTentarCadastrarEmprestimoComLimiteExcedido(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarEmprestimoComLimiteExcedido"));

		emprestimoLimiteExcedido.adicionarLivro(livro);
		emprestimoLimiteExcedido.adicionarLivro(livro2);
		emprestimoLimiteExcedido.adicionarLivro(livro3);
		emprestimoLimiteExcedido.adicionarLivro(livro4);
		emprestimoLimiteExcedido.adicionarLivro(livro5);
		
		emprestimoBC.cadastrar(emprestimoLimiteExcedido);
	}

}
