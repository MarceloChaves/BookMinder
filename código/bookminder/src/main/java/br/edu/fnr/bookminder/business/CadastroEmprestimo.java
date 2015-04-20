package br.edu.fnr.bookminder.business;

import java.util.ArrayList;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.edu.fnr.bookminder.entidades.Emprestimo;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoDuplicadoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemAlunoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemDataDevolucaoException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemDataException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemIdException;
import br.edu.fnr.bookminder.excecoes.emprestimo.EmprestimoSemLivroException;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.Controller;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@Controller
public class CadastroEmprestimo {

	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	private ArrayList<Emprestimo> cadastro = new ArrayList<Emprestimo>();

	
	public ArrayList<Emprestimo> getCadastro() {
		return cadastro;
	}

	public void setCadastro(ArrayList<Emprestimo> cadastro) {
		this.cadastro = cadastro;
	}
	
	
	public void cadastrar(Emprestimo emprestimo){
		
		validarDados(emprestimo);
		cadastro.add(emprestimo);
		logger.info(bundle.getString("cadastroEmprestimo.sucesso", emprestimo.getId(), emprestimo.getAluno().getNome()));
		
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
		}
		
	}
	
	public boolean estaCadastrado(Emprestimo emprestimo){
		return cadastro.contains(emprestimo);
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
	
}
