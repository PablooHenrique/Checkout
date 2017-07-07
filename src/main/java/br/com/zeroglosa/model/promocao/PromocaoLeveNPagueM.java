package br.com.zeroglosa.model.promocao;

public class PromocaoLeveNPagueM implements IPromocao{
	private int quantidadeASerComprada;
	private int quantidadeASerLevada;
	
	public PromocaoLeveNPagueM(int quantidadeASerComprada, int quantidadeASerLevada) {
		super();
		this.setQuantidadeASerComprada(quantidadeASerComprada);
		this.setQuantidadeASerLevada(quantidadeASerLevada);
	}

	@Override
	public double getPrecoEspecial(double precoUnitario, int quantidade) {
		double valorTotal = 0;
		if (quantidade > 0) {			
			if (quantidade >= this.quantidadeASerComprada){
				double valorTotalPromocao = getValorTotalProdutosEmPromocao(precoUnitario, quantidade);
				double valorTotalProdutosSemPromocao = getValorTotalProdutosSemPromocao(precoUnitario, quantidade);
				valorTotal = valorTotalPromocao + valorTotalProdutosSemPromocao;
				
				return valorTotal;
			}
			return getValorReal(precoUnitario, quantidade);
		}
		return valorTotal;
	}
	
	@Override
	public double getValorDesconto(double precoUnitario, int quantidade) {
		return getValorReal(precoUnitario, quantidade) - getPrecoEspecial(precoUnitario, quantidade);
	}

	private double getValorTotalProdutosSemPromocao(double precoUnitario, int quantidade) {
		int quantidadeProdutosEmPromocao = getQuantidadeProdutosEmPromocao(quantidade);
		return (quantidade - (quantidadeProdutosEmPromocao * this.quantidadeASerLevada)) * precoUnitario;
	}

	private double getValorTotalProdutosEmPromocao(double precoUnitario, int quantidade) {
		return (this.quantidadeASerComprada * precoUnitario) * getQuantidadeProdutosEmPromocao(quantidade);
	}
	
	private int getQuantidadeProdutosEmPromocao(int quantidade) {
		return quantidade / this.quantidadeASerLevada;
	}
	
	private double getValorReal(double precoUnitario, int quantidade){
		return precoUnitario * quantidade;
	}

	public int getQuantidadeASerComprada() {
		return quantidadeASerComprada;
	}

	public void setQuantidadeASerComprada(int quantidadeASerComprada) {
		this.quantidadeASerComprada = quantidadeASerComprada;
	}

	public int getQuantidadeASerLevada() {
		return quantidadeASerLevada;
	}

	public void setQuantidadeASerLevada(int quantidadeASerLevada) {
		this.quantidadeASerLevada = quantidadeASerLevada;
	}
}
