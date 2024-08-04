package br.com.desafio.produto.core.usecase;

import br.com.desafio.produto.core.domain.Produto;

public interface AdicionarProdutoNoCarrinhoUseCase {
    void execute(String carrinhoId, String produtoId);
}