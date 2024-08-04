package br.com.desafio.produto.core.gateway;

import br.com.desafio.produto.core.domain.Pedido;

import java.util.Optional;

public interface PedidoGateway {
    Pedido save(Pedido pedido);

    Optional<Pedido> findByCarrinhoId(String carrinhoId);
}