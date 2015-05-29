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

import br.edu.fnr.bookminder.business.LivroBC;
import br.edu.fnr.bookminder.entidades.Livro;
import br.edu.fnr.bookminder.excecoes.livro.LivroDuplicadoException;
import br.edu.fnr.bookminder.excecoes.livro.LivroSemAutorException;
import br.edu.fnr.bookminder.excecoes.livro.LivroSemCodigoException;
import br.edu.fnr.bookminder.excecoes.livro.LivroSemTituloException;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@RunWith(DemoiselleRunner.class)
public class CadastroLivroTeste {

	
	@Inject
	Logger logger;
	
	@Inject
	ResourceBundle bundle;
	
	@Inject
	Livro livro;
	
	@Inject
	Livro livroSemCodigo;
	
	@Inject
	Livro livroSemTitulo;
	
	@Inject
	Livro livroSemAutor;
	
	@Inject
	LivroBC livroBC;
	
	
	@Before
	public void setUp(){
	
		livro.setCodigo("01");
		livro.setAutor("J.R.R.Tolkien");
		livro.setTitulo("The Lord of Rings");
		
		livroSemCodigo.setAutor("No one important");
		livroSemCodigo.setTitulo("O Livro Sem Codigo");
		
		livroSemAutor.setCodigo("03");
		livroSemAutor.setTitulo("O Livro Sem Autor");
		
		livroSemTitulo.setCodigo("04 - untitled");
		livroSemTitulo.setAutor("Autor Desleixado");
				
	}
	
	@After
	public void tearDown(){
		
		List<Livro> livrosCadastrados = livroBC.findAll();
		List<String> livroIds = new ArrayList<String>();
		
		for(Livro livro : livrosCadastrados){
			
			livroIds.add(livro.getCodigo());
		}
		
		livroBC.delete(livroIds);
	}
	
	@Test 
	public void cadastrarLivroComSucesso(){
		
		logger.info(bundle.getString("processo.inicio", "cadastrarLivroComSucesso"));
		
		livroBC.cadastrar(livro);
		Assert.assertTrue(livroBC.estaCadastrado(livro));
		
		logger.info(bundle.getString("processo.fim", "cadastrarLivroComSucesso"));
	}
	
	@Test (expected = LivroDuplicadoException.class)
	public void falhaAoTentarCadastrarLivroDuplicado(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarLivroDuplicado"));
		livroBC.cadastrar(livro);
		livroBC.cadastrar(livro);
	}
	
	@Test (expected = LivroSemCodigoException.class)
	public void falhaAoTentarCadastrarLivroSemCodigo(){
		
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarLivroSemCodigo"));
		livroBC.cadastrar(livroSemCodigo);
	}
	
	@Test (expected = LivroSemAutorException.class)
	public void falhaAoTentarCadastrarLivroSemAutor(){
	
		logger.info(bundle.getString("processo.inicio", "falhaAoTentarCadastrarLivroSemAutor"));
		livroBC.cadastrar(livroSemAutor);
	}
	
	@Test (expected = LivroSemTituloException.class) 
	public void falharAoTentarCadastrarLivroSemTitulo(){

		logger.info(bundle.getString("processo.inicio", "falharAoTentarCadastrarLivroSemTitulo"));
		livroBC.cadastrar(livroSemTitulo);
		
	}
}
