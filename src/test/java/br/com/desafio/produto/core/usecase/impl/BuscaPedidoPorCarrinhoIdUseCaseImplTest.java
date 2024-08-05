package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.common.exception.PedidoNaoEncontradoException;
import br.com.desafio.produto.core.domain.Pedido;
import br.com.desafio.produto.core.gateway.PedidoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscaPedidoPorCarrinhoIdUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private BuscaPedidoPorCarrinhoIdUseCaseImpl buscaPedidoPorCarrinhoIdUseCase;

    @Test
    void deveRetornarPedidoQuandoCarrinhoIdExistir() {
        // Arrange
        String carrinhoId = "1";
        Pedido pedido = Pedido.builder()
                .build();
        when(pedidoGateway.findByCarrinhoId(carrinhoId)).thenReturn(Optional.of(pedido));

        // Act
        Pedido resultado = buscaPedidoPorCarrinhoIdUseCase.execute(carrinhoId);

        // Assert
        assertNotNull(resultado);
        assertEquals(pedido, resultado);
    }

    @Test
    void deveLancarExcecaoQuandoCarrinhoIdNaoExistir() {
        // Arrange
        String carrinhoId = "1";
        when(pedidoGateway.findByCarrinhoId(carrinhoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PedidoNaoEncontradoException.class, () ->
            buscaPedidoPorCarrinhoIdUseCase.execute(carrinhoId)
        );
    }
}