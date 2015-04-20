package br.edu.fnr.bookminder.business;

import java.util.ArrayList;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.edu.fnr.bookminder.entidades.Livro;
import br.edu.fnr.bookminder.excecoes.livro.LivroDuplicadoException;
import br.edu.fnr.bookminder.excecoes.livro.LivroSemAutorException;
import br.edu.fnr.bookminder.excecoes.livro.LivroSemCodigoException;
import br.edu.fnr.bookminder.excecoes.livro.LivroSemTituloException;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.Controller;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@Controller
public class CadastroLivro {

	private ArrayList<Livro> cadastro = new ArrayList<Livro>();
	
	@Inject
	Logger logger;
	
	@Inject
	ResourceBundle bundle;

	public ArrayList<Livro> getCadastro() {
		return cadastro;
	}

	public void setCadastro(ArrayList<Livro> cadastro) {
		this.cadastro = cadastro;
	}
	
	
	public void cadastrar(Livro livro){
		
		validarDados(livro);
		cadastro.add(livro);
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
		return cadastro.contains(livro);
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
