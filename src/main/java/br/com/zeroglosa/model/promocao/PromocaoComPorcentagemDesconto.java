package br.com.zeroglosa.model.promocao;

public class PromocaoComPorcentagemDesconto implements IPromocao{

	private int quantidadeProdutos;
	private double porcentagemDesconto;
	
	public PromocaoComPorcentagemDesconto(int quantidadeProdutos, double porcentagemDesconto) {
		super();
		this.quantidadeProdutos = quantidadeProdutos;
		this.porcentagemDesconto = porcentagemDesconto;
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
		valorProdutosEmPromocao = valorProdutosEmPromocao - Math.round(((valorProdutosEmPromocao * this.porcentagemDesconto) / 100));
		valorProdutosEmPromocao = valorProdutosEmPromocao * quantidadeProdutosEmPromocao;
		
		return valorProdutosEmPromocao;
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
