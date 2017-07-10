package br.com.zeroglosa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zeroglosa.model.Produto;
import br.com.zeroglosa.model.promocao.IPromocao;
import br.com.zeroglosa.model.promocao.PromocaoComPorcentagemDesconto;
import br.com.zeroglosa.model.promocao.PromocaoLeveNPagueM;

public class Repository {
	private static List<Produto> produtosDisponiveis = new ArrayList<Produto>();
	
	public void iniciarlizarBd(){
		List<IPromocao> promocoes = new ArrayList<IPromocao>();
		promocoes.add(new PromocaoComPorcentagemDesconto(3, 130));
		Produto produto = new Produto("A", 50.0, promocoes);
		produtosDisponiveis.add(produto);
		
		promocoes = new ArrayList<IPromocao>();
		promocoes.add(new PromocaoComPorcentagemDesconto(2, 45));
		produto = new Produto("B", 30, promocoes);
		produtosDisponiveis.add(produto);
		
		promocoes = new ArrayList<IPromocao>();
		promocoes.add(new PromocaoLeveNPagueM(2, 3));
		produto = new Produto("C", 20, promocoes);
		produtosDisponiveis.add(produto);
		
		produto = new Produto("D", 15, null);
		produtosDisponiveis.add(produto);
	}
	
	public Produto buscarProdutoPorNome(String nome){
		try {
			List<Produto> produtos = produtosDisponiveis.stream().filter(x->x.getNome().equals(nome)).collect(Collectors.toList());
			if (produtos.isEmpty()) {
				System.out.println("Produto não encontrado");
				return null;
			}
			return produtos.get(0).clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Não foi possivel consultar o produto");
			return null;
		}
	}
	
	public void adicionarProduto(Produto produto){
		produtosDisponiveis.add(produto);
	}
}
