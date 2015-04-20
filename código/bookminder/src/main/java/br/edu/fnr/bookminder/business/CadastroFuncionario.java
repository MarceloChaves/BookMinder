package br.edu.fnr.bookminder.business;

import java.util.ArrayList;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.edu.fnr.bookminder.entidades.Funcionario;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioDuplicadoException;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioSemCpfException;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioSemNomeException;
import br.edu.fnr.bookminder.excecoes.funcionario.FuncionarioSemSenhaException;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.Controller;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@Controller
public class CadastroFuncionario {

	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	private ArrayList<Funcionario> cadastro = new ArrayList<Funcionario>();


	public ArrayList<Funcionario> getCadastro() {
		return cadastro;
	}

	public void setCadastro(ArrayList<Funcionario> cadastro) {
		this.cadastro = cadastro;
	}

	public void cadastrar(Funcionario funcionario) {

		validarDados(funcionario);
		cadastro.add(funcionario);
		logger.info(bundle.getString("cadastroFuncionario.sucesso", funcionario.getNome(), funcionario.getCpf()));
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

		return cadastro.contains(funcionario);
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
