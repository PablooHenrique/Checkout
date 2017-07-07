package br.com.zeroglosa.model.promocao;

public interface IPromocao {
	public double getPrecoEspecial(double precoUnitario, int quantidade);
	public double getValorDesconto(double precoUnitario, int quantidade);
}
