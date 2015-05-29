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
import br.edu.fnr.bookminder.business.NotificacaoBC;
import br.edu.fnr.bookminder.entidades.Aluno;
import br.edu.fnr.bookminder.entidades.Emprestimo;
import br.edu.fnr.bookminder.entidades.Livro;
import br.edu.fnr.bookminder.entidades.Notificacao;
import br.edu.fnr.bookminder.excecoes.notificacoes.NotificacaoDuplicadaException;
import br.edu.fnr.bookminder.excecoes.notificacoes.NotificacaoSemEmprestimoException;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@RunWith(DemoiselleRunner.class)
public class CadastroNotificacaoTeste {

	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	@Inject
	private NotificacaoBC notificacaoBC;

	@Inject
	private EmprestimoBC emprestimoBC;
	
	@Inject
	private LivroBC livroBC;
	
	@Inject
	AlunoBC alunoBC;
	
	@Inject
	private Notificacao notificacao;

	@Inject
	private Notificacao notificacaoSemEmprestimo;

	@Inject
	private Emprestimo emprestimo;
	
	@Inject
	private Aluno aluno;
	
	@Inject
	Livro livro;

	@Before
	public void setUp() {

		livro.setAutor("Robert Kirkman");
		livro.setCodigo("TWDS");
		livro.setTitulo("The Walking Dead - A Ascenção do Governador");
		
		livroBC.cadastrar(livro);
		
		aluno.setEmail("duany.laissa@gmail.com");
		aluno.setMatricula("201110025");
		aluno.setNome("Duany Laíssa da Silva Santos");
		
		alunoBC.cadastrar(aluno);
		
		emprestimo.setDataEmprestimo("28/05/2015");
		emprestimo.setDataDevolução("04/05/2015");
		emprestimo.setAluno(aluno);
		emprestimoBC.adicionarLivroAoEmprestimo(livro, emprestimo);
		emprestimoBC.cadastrar(emprestimo);
		
		notificacao.setEmprestimo(emprestimo);
		
	}

	@After
	public void tearDown() {

		List<Notificacao> notificacoesCadastradas = notificacaoBC.findAll();
		List<String> notificacaoIds = new ArrayList<String>();

		List<Livro> livrosCadastrados = livroBC.findAll();
		List<String> livrosIds = new ArrayList<String>();
		
		List<Aluno> alunosCadastrados = alunoBC.findAll();
		List<String> alunosIds = new ArrayList<String>();
		
		List<Emprestimo> emprestimosCadastrados = emprestimoBC.findAll();
		List<String> emprestimosIds = new ArrayList<String>();
		
		
		for(Aluno aluno : alunosCadastrados){
			
			alunosIds.add(aluno.getMatricula());
		}
		for(Livro livro : livrosCadastrados){
			
			livrosIds.add(livro.getCodigo());
		}
		
		for(Emprestimo emprestimo : emprestimosCadastrados){
			
			emprestimosIds.add(emprestimo.getId());
		}
		
		for (Notificacao notificacao : notificacoesCadastradas) {

			notificacaoIds.add(notificacao.getId());
		}
	
		notificacaoBC.delete(notificacaoIds);
		emprestimoBC.delete(emprestimosIds);
		livroBC.delete(livrosIds);
		alunoBC.delete(alunosIds);
	
	}

	@Test
	public void cadastrarNotificacaoComSucesso() {

		logger.info(bundle.getString("processo.inicio","cadastrarNotificacaoComSucesso"));
		notificacaoBC.cadastrar(notificacao);
		
		Assert.assertTrue(notificacaoBC.estaCadastrado(notificacao));
		logger.info(bundle.getString("processo.fim","cadastrarNotificacaoComSucesso"));
	}

	@Test(expected = NotificacaoDuplicadaException.class)
	public void falhaAoTentarCadastrarNotificacaoDuplicada() {

		logger.info(bundle.getString("processo.inicio","falhaAoTentarCadastrarNotificacaoDuplicada"));
		notificacaoBC.cadastrar(notificacao);
		notificacaoBC.cadastrar(notificacao);

	}

	@Test(expected = NotificacaoSemEmprestimoException.class)
	public void falhaAoTentarCadastrarNotificacaoSemEmprestimo() {

		logger.info(bundle.getString("processo.inicio","falhaAoTentarCadastrarNotificacaoSemEmprestimo"));
		notificacaoBC.cadastrar(notificacaoSemEmprestimo);
	}
}
