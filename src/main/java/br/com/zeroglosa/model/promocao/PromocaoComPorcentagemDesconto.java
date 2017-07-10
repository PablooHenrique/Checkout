package br.com.zeroglosa.model.promocao;

public class PromocaoComPorcentagemDesconto implements IPromocao{

	private int quantidadeProdutos;
	private double valorComDesconto;
	
	public PromocaoComPorcentagemDesconto(int quantidadeProdutos, double valorComDesconto) {
		super();
		this.quantidadeProdutos = quantidadeProdutos;
		this.valorComDesconto = valorComDesconto;
	}

	@Override
	public double getPrecoEspecial(double precoUnitario, int quantidade) {
		double valorTotal = 0;
		if (quantidade > 0) {			
			if (quantidade >= this.quantidadeProdutos){
				double valorProdutosEmPromocao  = getValorProdutosEmPromocao(quantidade, precoUnitario);
				double valorProdutosSemPromocao = getValorProdutosSemPromocao(quantidade, precoUnitario);
				
				return valorProdutosSemPromocao + valorProdutosEmPromocao;
			}
			return getValorTotal(precoUnitario, quantidade);
		}
		return valorTotal;
	}

	@Override
	public double getValorDesconto(double precoUnitario, int quantidade) {
		return getValorTotal(precoUnitario, quantidade) - getPrecoEspecial(precoUnitario, quantidade);
	}
	
	private int getQuantidadeProdutosEmPromocao(int quantidade){
		return quantidade /  this.quantidadeProdutos;
	}
	
	private double getValorProdutosEmPromocao(int quantidade, double precoUnitario){
		int quantidadeProdutosEmPromocao = getQuantidadeProdutosEmPromocao(quantidade);
		double valorProdutosEmPromocao = (this.quantidadeProdutos * precoUnitario);
		valorProdutosEmPromocao = valorProdutosEmPromocao - Math.round(((valorProdutosEmPromocao * calcularPorcentagemDeDesconto(precoUnitario)) / 100));
		valorProdutosEmPromocao = valorProdutosEmPromocao * quantidadeProdutosEmPromocao;
		
		return valorProdutosEmPromocao;
	}
	
	private double calcularPorcentagemDeDesconto(double precoUnitario){
		Double valorReal = this.quantidadeProdutos * precoUnitario;
		return ( 100 - ( this.valorComDesconto * 100 ) / valorReal);
		
	}
	
	private double getValorProdutosSemPromocao(int quantidade, double precoUnitario){
		int produtosSemPromocao = getQuantidadeProdutosSemPromocao(quantidade);
		double valorProdutosSemPromocao = produtosSemPromocao * precoUnitario;
		
		return valorProdutosSemPromocao;
	}

	private int getQuantidadeProdutosSemPromocao(int quantidade) {
		return quantidade - (getQuantidadeProdutosEmPromocao(quantidade) * this.quantidadeProdutos);
	}
	
	private double getValorTotal(double precoUnitario, int quantidade) {
		return precoUnitario * quantidade;
	}
}
