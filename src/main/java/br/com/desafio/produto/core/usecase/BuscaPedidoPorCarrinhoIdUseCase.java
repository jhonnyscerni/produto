package br.com.desafio.produto.core.usecase;

import br.com.desafio.produto.core.domain.Pedido;

public interface BuscaPedidoPorCarrinhoIdUseCase {
    Pedido execute(String carrinhoId);
}