package br.com.desafio.produto.core.usecase;

import br.com.desafio.produto.core.domain.Pedido;

public interface CriarPedidoUseCase {
    Pedido execute(Pedido pedido);
}