package br.com.zeroglosa.model;

import java.util.List;

import br.com.zeroglosa.model.promocao.IPromocao;

public class Produto implements Cloneable{
	
	private String nome;
	private double precoUnitario;
	private List<IPromocao> promocoes;

	public Produto(String nome, double precoUnitario, List<IPromocao> promocoes) {
		super();
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.setPromocoes(promocoes);
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
		IPromocao promocao = obterPromocaoComMaiorDesconto(quantidade);
		if (promocao != null) {
			return promocao.getPrecoEspecial(getPrecoUnitario(), quantidade);
		}
		return this.precoUnitario * quantidade;
	}
	
	public double getValorDesconto(int quantidade){
		IPromocao promocao = obterPromocaoComMaiorDesconto(quantidade);
		if (promocao != null) {
			return promocao.getValorDesconto(getPrecoUnitario(), quantidade);			
		}
		return 0;
	}
	
	private IPromocao obterPromocaoComMaiorDesconto(int quantidade){
		double maiorDesconto = Integer.MIN_VALUE;
		int posicao = 0;
		if ((getPromocoes() != null) && (!getPromocoes().isEmpty())) {
			if(getPromocoes().size() > 1){
				for (int i = 0; i <= getPromocoes().size() - 1; i++) {
					double desconto = getPromocoes().get(i).getValorDesconto(getPrecoUnitario(), quantidade);
					if (desconto > maiorDesconto) {
						maiorDesconto = desconto;
						posicao = i;
					}
				}
				return getPromocoes().get(posicao);
			}
			return promocoes.get(0);
		}
		return null;
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

	public List<IPromocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<IPromocao> promocoes) {
		this.promocoes = promocoes;
	}
}
