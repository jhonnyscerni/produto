package br.com.desafio.produto.core.usecase;

public interface RemoverProdutoDoCarrinhoUseCase {
    void execute(String carrinhoId, String produtoId);
}