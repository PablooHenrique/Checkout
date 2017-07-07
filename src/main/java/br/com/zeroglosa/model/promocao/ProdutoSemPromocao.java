package br.com.zeroglosa.model.promocao;

public class ProdutoSemPromocao implements IPromocao {

	@Override
	public double getPrecoEspecial(double precoUnitario, int quantidade) {
		return precoUnitario * quantidade;
	}

	@Override
	public double getValorDesconto(double precoUnitario, int quantidade) {
		return 0;
	}

}
