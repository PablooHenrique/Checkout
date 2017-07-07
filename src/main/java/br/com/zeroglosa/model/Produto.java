package br.com.zeroglosa.model;

import br.com.zeroglosa.model.promocao.IPromocao;

public class Produto implements Cloneable{
	
	private String nome;
	private double precoUnitario;
	private IPromocao promocao;

	public Produto(String nome, double precoUnitario, IPromocao promocao) {
		super();
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.promocao = promocao;
	}
	
	@Override
	public boolean equals(Object produto) {
		try {
			return ((Produto) produto).getNome().equals(getNome());
		} catch (Exception e) {
			return false;
		}
	}
	
	public Produto clone() throws CloneNotSupportedException{
		return (Produto) super.clone();
	}

	public double getPrecoEspecial(int quantidade) {
		 return this.promocao.getPrecoEspecial(this.precoUnitario, quantidade);
	}
	
	public double getValorDesconto(int quantidade){
		return this.promocao.getValorDesconto(this.precoUnitario, quantidade);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public IPromocao getPromocao() {
		return promocao;
	}

	public void setPromocao(IPromocao promocao) {
		this.promocao = promocao;
	}
}
