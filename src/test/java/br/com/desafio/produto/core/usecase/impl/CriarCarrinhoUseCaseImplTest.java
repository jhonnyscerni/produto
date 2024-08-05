package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.domain.Carrinho;
import br.com.desafio.produto.core.gateway.CarrinhoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarCarrinhoUseCaseImplTest {

    @Mock
    private CarrinhoGateway carrinhoGateway;

    @InjectMocks
    private CriarCarrinhoUseCaseImpl criarCarrinhoUseCase;

    @Test
    void deveCriarCarrinhoComSucesso() {
        // Arrange
        Carrinho carrinho = new Carrinho();
        when(carrinhoGateway.save(carrinho)).thenReturn(carrinho);

        // Act
        Carrinho resultado = criarCarrinhoUseCase.execute(carrinho);

        // Assert
        assertNotNull(resultado);
        assertEquals(carrinho, resultado);
        verify(carrinhoGateway, times(1)).save(carrinho);
    }
}