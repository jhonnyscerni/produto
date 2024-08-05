package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.domain.Carrinho;
import br.com.desafio.produto.core.domain.Produto;
import br.com.desafio.produto.core.gateway.CarrinhoGateway;
import br.com.desafio.produto.core.gateway.PedidoGateway;
import br.com.desafio.produto.core.gateway.ProdutoGateway;
import br.com.desafio.produto.core.usecase.RemoverProdutoDoCarrinhoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoverProdutoDoCarrinhoUseCaseImpl implements RemoverProdutoDoCarrinhoUseCase {

    private final CarrinhoGateway carrinhoGateway;
    private final ProdutoGateway produtoGateway;
    private final PedidoGateway pedidoGateway;

    @Override
    public void execute(String carrinhoId, String produtoId) {
        Carrinho carrinho = carrinhoGateway.findById(carrinhoId)
                .orElseThrow(() -> new IllegalArgumentException("Carrinho not found"));
        Produto produto = produtoGateway.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto not found"));
        carrinho.getProdutos().removeIf(p -> p.getId().equals(produto.getId()));

        pedidoGateway.findByCarrinhoId(carrinhoId)
                .ifPresent(pedido -> {
                    pedido.setCarrinho(carrinho);
                    pedidoGateway.atulizarPedido(pedido);
                });
        carrinhoGateway.save(carrinho);
    }
}