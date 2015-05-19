package br.edu.fnr.bookminder.business;

import java.util.List;

import br.gov.frameworkdemoiselle.lifecycle.Shutdown;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import br.edu.fnr.bookminder.entidades.Notificacao;
import br.edu.fnr.bookminder.excecoes.notificacoes.NotificacaoDuplicadaException;
import br.edu.fnr.bookminder.excecoes.notificacoes.NotificacaoSemEmprestimoException;
import br.edu.fnr.bookminder.excecoes.notificacoes.NotificacaoSemIdException;
import br.edu.fnr.bookminder.persistence.NotificacaoDAO;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@BusinessController
public class NotificacaoBC extends DelegateCrud<Notificacao, String, NotificacaoDAO> {

	@Inject
	private	Logger logger;

	@Inject
	private ResourceBundle bundle;
	
	@Inject
	private	NotificacaoDAO notificacaoDAO;
	
	/*public ArrayList<Notificacao> getCadastro() {
		return cadastro;
	}

	public void setCadastro(ArrayList<Notificacao> cadastro) {
		
	}*/
	
	@Transactional
	@RequiredPermission(resource = "notificacao", operation = "cadastrar")
	public void cadastrar(Notificacao notificacao){
		
		validarDados(notificacao);
		notificacaoDAO.insert(notificacao);
		logger.info(bundle.getString("cadastroNotificacao.sucesso", notificacao.getId()));
	}

	@RequiredPermission(resource = "notificacao", operation = "validar")
	private void validarDados(Notificacao notificacao) {

		if(notificacao.getId() == null){
			throw new NotificacaoSemIdException(bundle.getString("cadastroNotificacao.erroSemId"));
			
		}else if(estaCadastrado(notificacao)){
			throw new NotificacaoDuplicadaException(bundle.getString("cadastroNotificacao.erroDuplicado"));
			
		}else if(notificacao.getEmprestimo()==null){
			throw new NotificacaoSemEmprestimoException(bundle.getString("cadastroNotificacao.erroSemEmprestimo"));
		}
	}
	
	@RequiredPermission(resource = "notificacao", operation = "consultar")
	public boolean estaCadastrado(Notificacao notificacao){
		return obterNotificacoesCadastradas().contains(notificacao);
	}
	
	public List<Notificacao> obterNotificacoesCadastradas(){
	
		return notificacaoDAO.findAll(); 
	}
	@Startup
	public void iniciar(){
		logger.info("Iniciando...! ò_Ó");
	}
	
	@Shutdown
	public void finalizar(){
		logger.info("Acabou a porcaria!! @__@");
	}
	
	/*@Transactional
	public void deleteTable(){
		em.createQuery("drop table Notificacao");
	}*/
	@ExceptionHandler
	public void tratarSemId(NotificacaoSemIdException e){
		
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarNotificacaoSemId"));
		throw e;
		
	}
	
	@ExceptionHandler
	public void tratarDuplicada(NotificacaoDuplicadaException e){
		
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarNotificacaoDuplicada"));
		throw e;
	}
	
	@ExceptionHandler
	public void tratarSemEmprestimo(NotificacaoSemEmprestimoException e){
		
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarNotificacaoSemEmprestimo"));
		throw e;
	}

}
