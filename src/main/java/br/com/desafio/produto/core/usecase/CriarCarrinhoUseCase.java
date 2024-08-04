package br.com.desafio.produto.core.usecase;

import br.com.desafio.produto.core.domain.Carrinho;

public interface CriarCarrinhoUseCase {
    Carrinho execute(Carrinho carrinho);
}