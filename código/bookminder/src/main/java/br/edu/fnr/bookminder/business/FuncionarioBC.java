package br.edu.fnr.bookminder.business;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import br.edu.fnr.bookminder.entidades.Funcionario;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioDuplicadoException;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioSemCpfException;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioSemNomeException;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioSemSenhaException;
import br.edu.fnr.bookminder.persistence.FuncionarioDAO;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.lifecycle.Shutdown;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@BusinessController
public class FuncionarioBC extends DelegateCrud<Funcionario, String, FuncionarioDAO> {

	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	@Inject
	private FuncionarioDAO funcionarioDAO;
	

	/*public ArrayList<Funcionario> getCadastro() {
		return cadastro;
	}

	public void setCadastro(ArrayList<Funcionario> cadastro) {
		this.cadastro = cadastro;
	}*/

	@Transactional
	public void cadastrar(Funcionario funcionario) {

		validarDados(funcionario);
		funcionarioDAO.insert(funcionario);
		logger.info(bundle.getString("cadastroFuncionario.sucesso", funcionario.getNome(), funcionario.getCpf()));
	}

	public List<Funcionario> obterFuncionariosCadastrados(){
		return funcionarioDAO.findAll();
	}
	private void validarDados(Funcionario funcionario){

		if(funcionario.getCpf() == null){
			throw new FuncionarioSemCpfException(bundle.getString("cadastroFuncionario.erroSemCPF"));

		}else if(estaCadastrado(funcionario)){
			throw new FuncionarioDuplicadoException(bundle.getString("cadastroFuncionario.erroDuplicado"));

		}else if(funcionario.getNome() == null){
			throw new FuncionarioSemNomeException(bundle.getString("cadastroFuncionario.erroSemNome"));

		}else if(funcionario.getSenha() == null){
			throw new FuncionarioSemSenhaException(bundle.getString("cadastroFuncionario.erroSemSenha"));

		}


	}

	public boolean estaCadastrado(Funcionario funcionario) {

		return obterFuncionariosCadastrados().contains(funcionario);
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
	public void tratarDuplicado(FuncionarioDuplicadoException e){

		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarFuncionarioDuplicado"));
		throw e;
	}

	@ExceptionHandler
	public void tratarSemCpf(FuncionarioSemCpfException e){

		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarFuncionarioSemCPF"));
		throw e;
	}

	@ExceptionHandler
	public void tratarSemNome(FuncionarioSemNomeException e){

		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falharAoTentarCadastrarFuncionarioSemNome"));
		throw e;
	}

	@ExceptionHandler
	public void tratarSemSenha(FuncionarioSemSenhaException e){

		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarFuncionarioSemSenha"));
		throw e;
	}
}
