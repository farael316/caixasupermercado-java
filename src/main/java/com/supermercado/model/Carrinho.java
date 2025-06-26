package com.supermercado.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<ItemCompra> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Produto produto, int quantidade) {
        // Verificar se o produto já está no carrinho
        for (ItemCompra item : itens) {
            if (item.getProduto().getCodigo().equals(produto.getCodigo())) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }
        
        // Se não encontrou, adiciona novo item
        itens.add(new ItemCompra(produto, quantidade));
    }

    public void removerItem(int index) {
        if (index >= 0 && index < itens.size()) {
            itens.remove(index);
        }
    }

    public void limparCarrinho() {
        itens.clear();
    }

    public List<ItemCompra> getItens() {
        return new ArrayList<>(itens);
    }

    public double getTotal() {
        double total = 0.0;
        for (ItemCompra item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public int getQuantidadeItens() {
        return itens.size();
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }
} 