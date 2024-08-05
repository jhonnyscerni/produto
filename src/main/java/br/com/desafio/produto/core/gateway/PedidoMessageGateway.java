package br.com.desafio.produto.core.gateway;

import br.com.desafio.produto.core.domain.Pedido;

public interface PedidoMessageGateway {
    void sendMessage(Pedido pedido);
}