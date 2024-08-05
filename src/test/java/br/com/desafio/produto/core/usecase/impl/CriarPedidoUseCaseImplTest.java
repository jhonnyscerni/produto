package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.domain.Pedido;
import br.com.desafio.produto.core.gateway.PedidoGateway;
import br.com.desafio.produto.core.gateway.PedidoMessageGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarPedidoUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private PedidoMessageGateway pedidoMessageGateway;

    @InjectMocks
    private CriarPedidoUseCaseImpl criarPedidoUseCase;

    @Test
    void deveCriarPedidoComSucesso() {
        // Arrange
        Pedido pedido = new Pedido();
        when(pedidoGateway.save(pedido)).thenReturn(pedido);

        // Act
        Pedido resultado = criarPedidoUseCase.execute(pedido);

        // Assert
        assertNotNull(resultado);
        assertEquals(pedido, resultado);
        verify(pedidoGateway, times(1)).save(pedido);
        verify(pedidoMessageGateway, times(1)).sendMessage(pedido);
    }

}