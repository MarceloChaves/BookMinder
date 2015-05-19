package br.edu.fnr.bookminder.business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import br.edu.fnr.bookminder.config.CadastroEmprestimoConfig;
import br.edu.fnr.bookminder.entidades.Emprestimo;
import br.edu.fnr.bookminder.entidades.Livro;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoDuplicadoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoLimiteExcedidoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemAlunoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemDataDevolucaoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemDataException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemIdException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemLivroException;
import br.edu.fnr.bookminder.persistence.EmprestimoDAO;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.lifecycle.Shutdown;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@BusinessController
public class EmprestimoBC extends DelegateCrud<Emprestimo, String, EmprestimoDAO> {

	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	@Inject
	private CadastroEmprestimoConfig config;
	
	@Inject
	private EmprestimoDAO emprestimoDAO;

	
	/*public ArrayList<Emprestimo> getCadastro() {
		return cadastro;
	}

	public void setCadastro(ArrayList<Emprestimo> cadastro) {
		this.cadastro = cadastro;
	}
*/
	@Transactional
	public void cadastrar(Emprestimo emprestimo){

		validarDados(emprestimo);
		emprestimoDAO.insert(emprestimo);
		logger.info(bundle.getString("cadastroEmprestimo.sucesso", emprestimo.getId(), emprestimo.getAluno().getNome()));

	}
	
	public List<Emprestimo> obterEmprestimosCadastrados(){

		return emprestimoDAO.findAll();
	}

	private void validarDados(Emprestimo emprestimo) {

		if(emprestimo.getId() == null){
			throw new EmprestimoSemIdException(bundle.getString("cadastroEmprestimo.erroSemId"));

		}else if(estaCadastrado(emprestimo)){

			throw new EmprestimoDuplicadoException(bundle.getString("cadastroEmprestimo.erroDuplicado"));

		}else if (emprestimo.getAluno() == null){
			throw new EmprestimoSemAlunoException(bundle.getString("cadastroEmprestimo.erroSemAluno"));

		}else if(emprestimo.getLivrosEmprestados().isEmpty()){
			throw new EmprestimoSemLivroException(bundle.getString("cadastroEmprestimo.erroSemLivro"));

		}else if (emprestimo.getDataEmprestimo() == null){
			throw new EmprestimoSemDataException(bundle.getString("cadastroEmprestimo.erroSemData"));

		}else if (emprestimo.getDataDevolução() == null){
			throw new EmprestimoSemDataDevolucaoException(bundle.getString("cadastroEmprestimo.erroSemDataDevolucao"));

		}else if(emprestimo.getLivrosEmprestados().size() > config.getLimiteEmprestimos()){
			throw new EmprestimoLimiteExcedidoException(bundle.getString("cadastroEmprestimo.erroLimiteExcedido"));

		}
	}
	
	public void adicionarLivroAoEmprestimo(Livro livro, Emprestimo emprestimo){
		
		if(emprestimo.getLivrosEmprestados().size() > config.getLimiteEmprestimos()){
			throw new EmprestimoLimiteExcedidoException(bundle.getString("cadastroEmprestimo.erroLimiteExcedido"));
		}else{
			emprestimo.adicionarLivro(livro);
		}
	}

	public boolean estaCadastrado(Emprestimo emprestimo){
		return obterEmprestimosCadastrados().contains(emprestimo);
	}

	@Startup
	public void iniciar(){
		logger.info("Iniciando...! ò_Ó");
	}
	
	@Shutdown
	public void finalizar(){
		logger.info("Acabou a porcaria!! @__@");
	}
	
	@ExceptionHandler
	public void tratarDuplicado(EmprestimoDuplicadoException e){

		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarEmprestimosoDuplicado"));
		throw e;
	}

	@ExceptionHandler
	public void tratarSemId(EmprestimoSemIdException e){

		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarEmprestimoSemId"));
		throw e;
	}

	@ExceptionHandler
	public void tratarSemAluno(EmprestimoSemAlunoException e){
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falharAoTentarCadastrarEmprestimoSemAluno"));
		throw e;
	}


	@ExceptionHandler
	public void tratarSemLivro(EmprestimoSemLivroException e){
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarEmprestimoSemLivro"));
		throw e;
	}

	@ExceptionHandler
	public void tratarSemData(EmprestimoSemDataException e){
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarEmprestimoSemData"));
		throw e;
	}

	@ExceptionHandler
	public void tratarSemDataDevolucao(EmprestimoSemDataDevolucaoException e){
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarEmprestimoSemDataDevolucao"));
		throw e;
	}

	@ExceptionHandler
	public void tratarLimiteExcedido(EmprestimoLimiteExcedidoException e){

		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarEmprestimoComLimiteExcedido"));
		throw e;
	}
}
