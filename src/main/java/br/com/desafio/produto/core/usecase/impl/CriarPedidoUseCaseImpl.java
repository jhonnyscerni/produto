package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.domain.Pedido;
import br.com.desafio.produto.core.gateway.PedidoGateway;
import br.com.desafio.produto.core.usecase.CriarPedidoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarPedidoUseCaseImpl implements CriarPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    @Override
    public Pedido execute(Pedido pedido) {
        return pedidoGateway.save(pedido);
    }
}