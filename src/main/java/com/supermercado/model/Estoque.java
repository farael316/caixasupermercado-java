package com.supermercado.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estoque {
    private Map<String, Produto> produtos;

    public Estoque() {
        this.produtos = new HashMap<>();
        inicializarProdutos();
    }

    private void inicializarProdutos() {
        // Lista de 20 produtos comuns com código, nome, preço e quantidade em estoque
        adicionarProduto(new Produto("001", "Arroz Integral", 8.50, 50));
        adicionarProduto(new Produto("002", "Feijão Preto", 6.80, 40));
        adicionarProduto(new Produto("003", "Macarrão Espaguete", 3.20, 60));
        adicionarProduto(new Produto("004", "Óleo de Soja", 7.90, 30));
        adicionarProduto(new Produto("005", "Leite Integral", 4.50, 80));
        adicionarProduto(new Produto("006", "Pão de Forma", 5.20, 25));
        adicionarProduto(new Produto("007", "Queijo Mussarela", 12.80, 35));
        adicionarProduto(new Produto("008", "Presunto", 8.90, 30));
        adicionarProduto(new Produto("009", "Banana", 4.80, 100));
        adicionarProduto(new Produto("010", "Maçã", 6.50, 70));
        adicionarProduto(new Produto("011", "Tomate", 5.90, 45));
        adicionarProduto(new Produto("012", "Cebola", 3.40, 60));
        adicionarProduto(new Produto("013", "Batata", 4.20, 80));
        adicionarProduto(new Produto("014", "Coca-Cola 2L", 8.50, 40));
        adicionarProduto(new Produto("015", "Suco de Laranja", 6.80, 35));
        adicionarProduto(new Produto("016", "Sabão em Pó", 12.50, 25));
        adicionarProduto(new Produto("017", "Papel Higiênico", 8.90, 30));
        adicionarProduto(new Produto("018", "Shampoo", 15.80, 20));
        adicionarProduto(new Produto("019", "Escova de Dentes", 4.50, 40));
        adicionarProduto(new Produto("020", "Pasta de Dente", 3.90, 50));
    }

    public void adicionarProduto(Produto produto) {
        produtos.put(produto.getCodigo(), produto);
    }

    public Produto buscarPorCodigo(String codigo) {
        return produtos.get(codigo);
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos.values());
    }

    public boolean verificarDisponibilidade(String codigo, int quantidade) {
        Produto produto = produtos.get(codigo);
        return produto != null && produto.getQuantidadeEstoque() >= quantidade;
    }

    public void decrementarEstoque(String codigo, int quantidade) {
        Produto produto = produtos.get(codigo);
        if (produto != null) {
            produto.decrementarEstoque(quantidade);
        }
    }
} 