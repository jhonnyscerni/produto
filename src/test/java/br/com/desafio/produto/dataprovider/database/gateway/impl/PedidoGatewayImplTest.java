package br.com.desafio.produto.dataprovider.database.gateway.impl;

import br.com.desafio.produto.core.domain.Pedido;
import br.com.desafio.produto.dataprovider.database.repository.PedidoRepository;
import br.com.desafio.produto.dataprovider.database.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoGatewayImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private PedidoGatewayImpl pedidoGateway;

    @Test
    void deveRetornarVazioQuandoPedidoNaoForEncontradoPorCarrinhoId() {
        // Arrange
        String carrinhoId = "1";
        when(pedidoRepository.findByCarrinhoId(carrinhoId)).thenReturn(new ArrayList<>());

        // Act
        Optional<Pedido> result = pedidoGateway.findByCarrinhoId(carrinhoId);

        // Assert
        assertFalse(result.isPresent());
        verify(pedidoRepository, times(1)).findByCarrinhoId(carrinhoId);
    }
}