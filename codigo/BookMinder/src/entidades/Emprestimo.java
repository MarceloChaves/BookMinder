package entidades;

public class Emprestimo {

	private String id;
	private String autor;
	private String titulo;
	private String dataEmprestimo;
	private String dataDevolucao;

	public Emprestimo(String id, String autor, String titulo,
			String dataEmprestimo, String dataDevoulucao) {
		super();
		this.id = id;
		this.autor = autor;
		this.titulo = titulo;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevoulucao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
