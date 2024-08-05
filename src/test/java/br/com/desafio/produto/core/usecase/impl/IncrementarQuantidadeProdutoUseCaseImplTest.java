package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.domain.Produto;
import br.com.desafio.produto.core.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IncrementarQuantidadeProdutoUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private IncrementarQuantidadeProdutoUseCaseImpl incrementarQuantidadeProdutoUseCase;

    @Test
    void deveIncrementarQuantidadeComSucesso() {
        // Arrange
        Produto produto = new Produto();
        List<Produto> produtos = Collections.singletonList(produto);

        // Act
        incrementarQuantidadeProdutoUseCase.incrementarQuantidade(produtos);

        // Assert
        verify(produtoGateway, times(1)).incrementarQuantidade(produtos);
    }

}