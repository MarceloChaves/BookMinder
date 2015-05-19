package br.edu.fnr.bookminder.business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import br.edu.fnr.bookminder.entidades.Aluno;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoDuplicadoException;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoSemEmailException;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoSemMatriculaException;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoSemNomeException;
import br.edu.fnr.bookminder.persistence.AlunoDAO;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.lifecycle.Shutdown;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, String, AlunoDAO> {

	
	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	@Inject
	private AlunoDAO alunoDAO;

	/*public ArrayList<Aluno> getCadastro() {
		return cadastro;
	}

	public void setCadastro(ArrayList<Aluno> cadastro) {
		this.cadastro = cadastro;
	}*/
	
	@Transactional
	public void cadastrar(Aluno aluno){
		
		validarDados(aluno);
		alunoDAO.insert(aluno);
		logger.info(bundle.getString("cadastroAluno.sucesso", aluno.getNome(), aluno.getMatricula()));
	}
	
	public List<Aluno> obterAlunosCadastrados(){
		return alunoDAO.findAll();
	}
				
	private void validarDados(Aluno aluno) {
		
		if(aluno.getMatricula() == null){
			throw new AlunoSemMatriculaException(bundle.getString("cadastroAluno.erroSemMatricula"));
		
		}else if(estaCadastrado(aluno)){
			throw new AlunoDuplicadoException(bundle.getString("cadastroAluno.erroDuplicado"));
		}
		else if(aluno.getNome() == null){
			throw new AlunoSemNomeException(bundle.getString("cadastroAluno.erroSemNome"));
		
		}else if(aluno.getEmail() == null){
			throw new AlunoSemEmailException(bundle.getString("cadastroAluno.erroSemEmail"));
		}
	}

	public boolean estaCadastrado(Aluno aluno) {
		return obterAlunosCadastrados().contains(aluno);
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
	public void tratarDuplicado(AlunoDuplicadoException e){
		
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarAlunoDuplicado"));
		throw e;
	}
	
	@ExceptionHandler
	public void tratarSemMatricula(AlunoSemMatriculaException e){
	
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarAlunoSemMatricula"));
		throw e;
	}
	
	@ExceptionHandler
	public void tratarSemNome(AlunoSemNomeException e){
		
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarAlunoSemNome"));
		throw e;
	}
	
	@ExceptionHandler
	public void tratarSemEmail(AlunoSemEmailException e){
		
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarAlunoSemEmail"));
		throw e;
	}
}



