package br.com.zeroglosa.teste.checkout;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.zeroglosa.controller.Checkout;
import br.com.zeroglosa.model.Produto;
import br.com.zeroglosa.model.promocao.IPromocao;
import br.com.zeroglosa.model.promocao.PromocaoComPorcentagemDesconto;
import br.com.zeroglosa.repository.Repository;

public class TesteCheckout {
	private Checkout checkout;
	private Repository repository;
	
	@Before
	public void init(){
		checkout 	= new Checkout();
		repository 	= new Repository();
		repository.iniciarlizarBd();
	}

	@Test
	public void testaInsercaoCarrinhoCompras(){
		checkout.add("A");
		Assert.assertTrue(checkout.getQuantidadeItensCarrinho() == 1);
	}
	
	@Test
	public void testaQuantidadeItensInseridasNoCarrinhoDoMesmoProduto(){
		checkout.add("A");
		checkout.add("A");
		Assert.assertTrue(checkout.getQuantidadeItensCarrinho() == 1);
	}
	
	@Test
	public void testaQuantidadeDoMesmoProdutoInseridasNoCarrinho(){
		checkout.add("A");
		checkout.add("A");
		Assert.assertTrue(checkout.getQuantidadeItensPorProduto("A") == 2);
	}
	
	@Test
	public void testaRemocaoCarrinhoCompras(){
		checkout.add("A");
		checkout.remove("A");
		Assert.assertTrue(checkout.getQuantidadeItensCarrinho() == 0);
	}
	
	@Test
	public void testaRemocaoCarrinhoComprasComProdutoComQuantidadeAcimaDeUm(){
		checkout.add("A");
		checkout.add("A");
		checkout.remove("A");
		Assert.assertTrue(checkout.getQuantidadeItensCarrinho() == 1);
	}
	
	@Test
	public void testaRemocaoCarrinhoComprasQuantidadeDoProdutoComProdutoComQuantidadeAcimaDeUm(){
		checkout.add("A");
		checkout.add("A");
		checkout.remove("A");
		Assert.assertTrue(checkout.getQuantidadeItensPorProduto("A") == 1);
	}
	
	@Test
	public void testaValorCarrinhoComprasSemDesconto(){
		checkout.add("A");
		checkout.add("B");
		checkout.add("C");
		checkout.add("D");
		checkout.add("D");
		checkout.add("D");
		checkout.add("D");
		
		double desconto = checkout.getTotalDiscount();
		Assert.assertTrue(desconto == 0);
	}
	
	@Test
	public void testaValorDescontoProdutoA(){
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		
		double valorDesconto = checkout.getTotalDiscount();
		Assert.assertTrue(valorDesconto == 40);
	}

	@Test
	public void testaCasoDoisValorDescontoProdutoA(){
		checkout.add("A");
		checkout.add("A");
		
		double valorDesconto = checkout.getTotalDiscount();
		
		Assert.assertTrue(valorDesconto == 0);
	}
	
	@Test
	public void testeValorCarrinhoComDescontoProdutoA(){
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		
		double totalPrice = checkout.getTotalPrice();
		Assert.assertTrue(totalPrice == 130);
	}
	
	@Test
	public void testeLeveNPagueMCasoUm(){
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		
		double totalPrice = checkout.getTotalPrice();
		Assert.assertTrue(totalPrice == 60);
	}
	
	@Test
	public void testeLeveNPagueMCasoDois(){
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		
		double totalPrice = checkout.getTotalPrice();
		Assert.assertTrue(totalPrice == 80);
	}
	
	@Test
	public void testeLeveNPagueMCasoTres(){
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		
		double totalPrice = checkout.getTotalPrice();
		Assert.assertTrue(totalPrice == 100);
	}
	
	@Test
	public void testeProdutoSemDesconto(){
		checkout.add("D");
		checkout.add("D");
		checkout.add("D");
		checkout.add("D");
		
		double totalPrice = checkout.getTotalPrice();
		Assert.assertTrue(totalPrice == 60);
	}
	
	@Test
	public void testeProdutoComMaisDeUmaPromocao(){
		List<IPromocao> promocoes = new ArrayList<IPromocao>();
		
		promocoes.add(new PromocaoComPorcentagemDesconto(4, 200));
		promocoes.add(new PromocaoComPorcentagemDesconto(2, 150));
		
		Produto produto = new Produto("X", 100.0, promocoes);
		repository.adicionarProduto(produto);
		checkout.add("X");
		checkout.add("X");
		checkout.add("X");
		checkout.add("X");
		
		double totalPrice = checkout.getTotalPrice();
		Assert.assertTrue(totalPrice == 200);
	}
	
	@Test
	public void testeProdutoComMaisDeUmaPromocaoValorDesconto(){
		List<IPromocao> promocoes = new ArrayList<IPromocao>();
		
		promocoes.add(new PromocaoComPorcentagemDesconto(4, 200));
		promocoes.add(new PromocaoComPorcentagemDesconto(2, 150));
		
		Produto produto = new Produto("X", 100.0, promocoes);
		repository.adicionarProduto(produto);
		checkout.add("X");
		checkout.add("X");
		checkout.add("X");
		checkout.add("X");
		
		double totalDiscont = checkout.getTotalDiscount();
		Assert.assertTrue(totalDiscont == 200);
	}
	
	//Casos de teste relativos a prova para desconto e Valor Total
	
	@Test
	public void testeCasoTesteUmValorFinal(){
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		checkout.remove("A");
		
		double totalPrice = checkout.getTotalPrice();
		Assert.assertTrue(totalPrice == 230);
	}
	
	@Test
	public void testeCasoTesteUmTotalDesconto(){
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		checkout.add("A");
		checkout.remove("A");
		
		double valorDesconto = checkout.getTotalDiscount();
		Assert.assertTrue(valorDesconto == 20);
	}
	
	@Test
	public void testeCasoTesteDoisValorFinal(){
		checkout.add("D");
		checkout.add("A");
		checkout.add("B");
		checkout.add("A");
		checkout.add("B");
		checkout.add("A");
		checkout.remove("A");
		checkout.remove("B");
		
		double totalPrice = checkout.getTotalPrice();
		Assert.assertTrue(totalPrice == 145);
	}
	
	@Test
	public void testeCasoTesteDoisTotalDesconto(){
		checkout.add("D");
		checkout.add("A");
		checkout.add("B");
		checkout.add("A");
		checkout.add("B");
		checkout.add("A");
		checkout.remove("A");
		checkout.remove("B");
		
		double totalDesconto = checkout.getTotalDiscount();
		Assert.assertTrue(totalDesconto == 0);
	}
	
	@Test
	public void testeCasoTesteTresValorFinal(){
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.remove("C");
		checkout.remove("C");
		
		double totalPrice = checkout.getTotalPrice();
		Assert.assertTrue(totalPrice == 40);
	}
	
	@Test
	public void testeCasoTesteTresValorDesconto(){
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.add("C");
		checkout.remove("C");
		checkout.remove("C");
		
		double totalDesconto = checkout.getTotalDiscount();
		Assert.assertTrue(totalDesconto == 0);
	}
	
	@Test
	public void testeCasoQuatroValorFinal(){
		checkout.add("C");
		checkout.add("B");
		checkout.add("B");
		checkout.remove("B");
		
		double totalPrice = checkout.getTotalPrice();
		Assert.assertTrue(totalPrice == 50);
	}
	
	@Test
	public void testeCasoQuatroValorDesconto(){
		checkout.add("C");
		checkout.add("B");
		checkout.add("B");
		checkout.remove("B");
		
		double totalDesconto = checkout.getTotalDiscount();
		Assert.assertTrue(totalDesconto == 0);
	}
}
