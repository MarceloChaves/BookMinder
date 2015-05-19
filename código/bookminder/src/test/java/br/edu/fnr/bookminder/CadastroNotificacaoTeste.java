package br.edu.fnr.bookminder;

import java.util.ArrayList;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import br.edu.fnr.bookminder.business.NotificacaoBC;
import br.edu.fnr.bookminder.entidades.Emprestimo;
import br.edu.fnr.bookminder.entidades.Notificacao;
import br.edu.fnr.bookminder.excecoes.notificacoes.NotificacaoDuplicadaException;
import br.edu.fnr.bookminder.excecoes.notificacoes.NotificacaoSemEmprestimoException;
import br.edu.fnr.bookminder.excecoes.notificacoes.NotificacaoSemIdException;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.util.ResourceBundle;


@RunWith(DemoiselleRunner.class)
public class CadastroNotificacaoTeste {

	
	@Inject
	private Logger logger;
	
	@Inject
	private ResourceBundle bundle;
	
	@Inject
	private NotificacaoBC cadastro;
	
	@Inject
	private Notificacao notificacao;
	
	@Inject
	private Notificacao notificacaoSemId;
	
	@Inject
	private Notificacao notificacaoSemEmprestimo;
	
	@Inject 
	private Emprestimo emprestimo;
	
	@Before
	public void setUp(){
		
		notificacao.setId("01");
		notificacao.setEmprestimo(emprestimo);
		
		notificacaoSemEmprestimo.setId("02");
		
		notificacaoSemId.setEmprestimo(emprestimo);
		
	}
	/*@After
	public void tearDown(){
		
		ArrayList<Notificacao> cadastroVazio = cadastro.getCadastro();
		cadastroVazio.clear();
		cadastro.setCadastro(cadastroVazio);
		
	}*/
	
	
	@Test
	public void cadastrarNotificacaoComSucesso(){
		
		logger.info(bundle.getString("processo.inicio", "cadastrarNotificacaoComSucesso"));
		cadastro.cadastrar(notificacao);
		Assert.assertTrue(cadastro.estaCadastrado(notificacao));
		logger.info(bundle.getString("processo.fim", "cadastrarNotificacaoComSucesso"));
	}
	
	@Test (expected = NotificacaoDuplicadaException.class)
	public void falhaAoTentarCadastrarNotificacaoDuplicada(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarNotificacaoDuplicada"));
		cadastro.cadastrar(notificacao);
		cadastro.cadastrar(notificacao);
		
	}
	
	@Test (expected = NotificacaoSemIdException.class)
	public void falhaAoTentarCadastrarNotificacaoSemId(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarNotificacaoSemId"));
		cadastro.cadastrar(notificacaoSemId);
		
	}
	
	@Test (expected = NotificacaoSemEmprestimoException.class)
	public void falhaAoTentarCadastrarNotificacaoSemEmprestimo(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarNotificacaoSemEmprestimo"));
		cadastro.cadastrar(notificacaoSemEmprestimo);
	}
}
