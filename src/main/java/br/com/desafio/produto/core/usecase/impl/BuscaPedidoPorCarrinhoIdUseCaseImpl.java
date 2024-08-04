package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.common.exception.PedidoNaoEncontradoException;
import br.com.desafio.produto.core.domain.Pedido;
import br.com.desafio.produto.core.gateway.PedidoGateway;
import br.com.desafio.produto.core.usecase.BuscaPedidoPorCarrinhoIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscaPedidoPorCarrinhoIdUseCaseImpl implements BuscaPedidoPorCarrinhoIdUseCase {

    private final PedidoGateway pedidoGateway;

    @Override
    public Pedido execute(String carrinhoId) {
        return pedidoGateway.findByCarrinhoId(carrinhoId).orElseThrow(
                () -> new PedidoNaoEncontradoException(carrinhoId));
    }
}