package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.gateway.CarrinhoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExcluirCarrinhoUseCaseImplTest {

    @Mock
    private CarrinhoGateway carrinhoGateway;

    @InjectMocks
    private ExcluirCarrinhoUseCaseImpl excluirCarrinhoUseCase;

    @Test
    void deveExcluirCarrinhoComSucesso() {
        // Arrange
        String carrinhoId = "1";

        // Act
        excluirCarrinhoUseCase.execute(carrinhoId);

        // Assert
        verify(carrinhoGateway, times(1)).delete(carrinhoId);
    }

}