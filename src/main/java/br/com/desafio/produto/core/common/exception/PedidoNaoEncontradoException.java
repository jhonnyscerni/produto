package br.com.desafio.produto.core.common.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(String pedidoId) {
        super("Pedido não encontrado com ID: " + pedidoId);
    }
}