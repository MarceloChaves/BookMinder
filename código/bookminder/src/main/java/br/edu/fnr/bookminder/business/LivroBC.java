package br.edu.fnr.bookminder.business;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.edu.fnr.bookminder.entidades.Livro;
import br.edu.fnr.bookminder.excecoes.livro.LivroDuplicadoException;
import br.edu.fnr.bookminder.excecoes.livro.LivroSemAutorException;
import br.edu.fnr.bookminder.excecoes.livro.LivroSemCodigoException;
import br.edu.fnr.bookminder.excecoes.livro.LivroSemTituloException;
import br.edu.fnr.bookminder.persistence.LivroDAO;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.lifecycle.Shutdown;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@BusinessController
public class LivroBC extends DelegateCrud<Livro, String, LivroDAO> {
	private static final long serialVersionUID = 4075724791446510998L;

	@Inject
	private Logger logger;
	
	@Inject
	private ResourceBundle bundle;

	@Inject
	private LivroDAO livroDAO; 
	
	public List<Livro> obterLivrosCadastrados(){
		return livroDAO.findAll();
	}
	
	@Transactional
	public void cadastrar(Livro livro){
		
		validarDados(livro);
		livroDAO.insert(livro);
		logger.info(bundle.getString("cadastroLivro.sucesso",livro.getTitulo(), livro.getAutor(), livro.getCodigo()));
		
			
	}

	private void validarDados(Livro livro) {
		
		if(livro.getCodigo() == null){
			throw new LivroSemCodigoException(bundle.getString("cadastroLivro.erroSemCodigo"));

		}else if(estaCadastrado(livro)){
			throw new LivroDuplicadoException(bundle.getString("cadastroLivro.erroDuplicado"));
			
		}else if(livro.getTitulo() == null){
			throw new LivroSemTituloException(bundle.getString("cadastroLivro.erroSemTitulo"));
	
		}else if (livro.getAutor() == null){
			throw new LivroSemAutorException(bundle.getString("cadastroLivro.erroSemAutor"));
			
		}
	}

	public boolean estaCadastrado(Livro livro) {
		return obterLivrosCadastrados().contains(livro);
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
	public void tratarDuplicado(LivroDuplicadoException e){
		
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarLivroDuplicado"));
		throw e;
	}
	
	@ExceptionHandler
	public void tratarSemCodigo(LivroSemCodigoException e){
	
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarLivroSemCodigo"));
		throw e;
	}
	
	@ExceptionHandler
	public void tratarSemAutor(LivroSemAutorException e){
		
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarLivroSemAutor"));
		throw e;
	}
	
	@ExceptionHandler
	public void tratarSemTitulo(LivroSemTituloException e){
		
		logger.warn(e.getMessage());
		logger.info(bundle.getString("processo.fim", "falhaAoTentarCadastrarLivroSemTitulo"));
		throw e;
	}
	
}
