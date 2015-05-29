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
import br.edu.fnr.bookminder.entidades.Aluno;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoDuplicadoException;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoSemEmailException;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoSemMatriculaException;
import br.edu.fnr.bookminder.excecoes.aluno.AlunoSemNomeException;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@RunWith (DemoiselleRunner.class)
public class CadastroAlunoTeste {

	@Inject
	Logger logger;
	
	@Inject 
	ResourceBundle bundle;
	
	@Inject
	AlunoBC alunoBC;
	
	@Inject
	Aluno aluno;
	
	@Inject
	Aluno alunoSemMatricula;
	
	@Inject
	Aluno alunoSemNome;
	
	@Inject
	Aluno alunoSemEmail;
	
	
	
	@Before
	public void setUp(){
		
		aluno.setMatricula("201110025");
		aluno.setNome("Duany Laíssa da Silva Santos");
		aluno.setEmail("duany.laissa@gmail.com");
		
		alunoSemEmail.setMatricula("02");
		alunoSemEmail.setNome("AlunoTeste Sem Email");
		
		alunoSemNome.setMatricula("03");
		alunoSemNome.setEmail("aluno.sem.nome@nameless.com");
		
		alunoSemMatricula.setNome("AlunoTeste Sem Matricula");
		alunoSemMatricula.setEmail("aluno.sem.matricula@untrackable.com");
		
	}
	@After
	public void tearDown(){
		
		List<Aluno> alunosCadastrados = alunoBC.findAll();
		List<String> alunoIds = new ArrayList<String>();
		
		for(Aluno aluno : alunosCadastrados){
			
			alunoIds.add(aluno.getMatricula());
		}
		alunoBC.delete(alunoIds);
	}
	
	@Test
	public void cadastrarAlunoComSucesso(){
		
		logger.info(bundle.getString("processo.inicio", "cadastrarAlunoComSucesso"));
		
		alunoBC.cadastrar(aluno);
		Assert.assertTrue(alunoBC.estaCadastrado(aluno));
		
		logger.info(bundle.getString("processo.fim", "cadastrarAlunoComSucesso"));
	}
	
	
	@Test (expected = AlunoDuplicadoException.class)
	public void falhaAoTentarCadastrarAlunoDuplicado(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarAlunoDuplicado"));
		alunoBC.cadastrar(aluno);
		alunoBC.cadastrar(aluno);
	}
	
	@Test (expected = AlunoSemMatriculaException.class)
	public void falhaAoTentarCadastarAlunoSemMatricula(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarAlunoSemMatricula"));
		alunoBC.cadastrar(alunoSemMatricula);
	}
	
	@Test (expected = AlunoSemNomeException.class)
	public void falhaAoTentarCadastrarAlunoSemNome(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarAlunoSemNome"));
		alunoBC.cadastrar(alunoSemNome);
	}
	
	@Test (expected = AlunoSemEmailException.class)
	public void falhaAoTentarCadastrarAlunoSemEmail(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarAlunoSemEmail"));
		alunoBC.cadastrar(alunoSemEmail);
	}
	
}
