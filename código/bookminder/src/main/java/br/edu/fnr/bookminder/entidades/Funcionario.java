package br.edu.fnr.bookminder.entidades;

public class Funcionario {
	
	private String nome;
	private String cpf;
	private String senha;
	
	
	public Funcionario(){
		
	}
	
	public Funcionario(String cpf){
		setCpf(cpf);
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public boolean equals(Object funcionario){
		
		String cpfObtido = ((Funcionario) funcionario).getCpf();
		
		return cpfObtido.equals(this.cpf);
	}
	

}
