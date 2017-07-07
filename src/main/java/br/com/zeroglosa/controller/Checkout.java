package br.com.zeroglosa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zeroglosa.model.Item;
import br.com.zeroglosa.model.Produto;
import br.com.zeroglosa.repository.Repository;

public class Checkout {
	private List<Item> itens;
	private Repository repository;
	
	public Checkout() {
		super();
		setItens(new ArrayList<Item>());
		setRepository(new Repository());
	}

	public void add(String tipoProduto) {
		Item item = consultarItemNoCarrinhoCompras(tipoProduto);
		if (item != null) {
			item.setQuantidade(item.getQuantidade() + 1);
		}else{
			Produto produto = repository.buscarProdutoPorNome(tipoProduto);
			this.itens.add(new Item(produto, 1));
		}
	}

	public void remove(String tipoProduto) {
		Item item = consultarItemNoCarrinhoCompras(tipoProduto);
		if (item != null) {
			if (item.getQuantidade() > 1) {
				item.setQuantidade(item.getQuantidade() - 1);
			}else{
				this.itens.remove(item);
			}
		}
	}
	
	public double getTotalPrice() {
		Double totalPrice = 0.0;
		for (Item item : itens) {
			totalPrice += item.getProduto().getPrecoEspecial(item.getQuantidade());
		}
		return totalPrice;
	}
	
	public double getTotalDiscount(){
		Double totalPrice = 0.0;
		for (Item item : itens) {
			totalPrice += item.getProduto().getValorDesconto(item.getQuantidade());
		}
		return totalPrice;
	}
	
	public int getQuantidadeItensCarrinho() {
		return this.itens.size();
	}
	
	public int getQuantidadeItensPorProduto(String tipoProduto){
		Produto produto = repository.buscarProdutoPorNome(tipoProduto);
		List<Item> itens = this.itens.stream().filter(x->x.getProduto().equals(produto)).collect(Collectors.toList());
		if (!itens.isEmpty()) {
			return itens.get(0).getQuantidade();
		}
		return 0;
	}

	private Item consultarItemNoCarrinhoCompras(String tipoProduto){
		Produto produto  = repository.buscarProdutoPorNome(tipoProduto);
		if (produto != null) {
			List<Item> itens = this.itens.stream().filter(x->x.getProduto().equals(produto)).collect(Collectors.toList());
			if (!itens.isEmpty()) {
				return itens.get(0);
			}
		}
		return null;
	}
	
	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

}
