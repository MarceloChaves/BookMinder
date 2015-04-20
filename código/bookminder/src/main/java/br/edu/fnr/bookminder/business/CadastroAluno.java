package br.edu.fnr.bookminder.business;

import java.util.ArrayList;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.edu.fnr.bookminder.entidades.Aluno;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoDuplicadoException;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoSemEmailException;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoSemMatriculaException;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoSemNomeException;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.Controller;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@Controller
public class CadastroAluno {

	
	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	private ArrayList<Aluno> cadastro = new ArrayList<Aluno>();

	public ArrayList<Aluno> getCadastro() {
		return cadastro;
	}

	public void setCadastro(ArrayList<Aluno> cadastro) {
		this.cadastro = cadastro;
	}
	
	public void cadastrar(Aluno aluno){
		
		validarDados(aluno);
		cadastro.add(aluno);
		logger.info(bundle.getString("cadastroAluno.sucesso", aluno.getNome(), aluno.getMatricula()));
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
		return cadastro.contains(aluno);
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



