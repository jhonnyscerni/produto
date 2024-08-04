package br.com.desafio.produto.core.common.exception;

public class CarrinhoComProdutosException extends RuntimeException {

    public CarrinhoComProdutosException(String id) {
        super("Carrinho não encontrado com o ID: " + id);
    }
}