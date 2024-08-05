package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.domain.Carrinho;
import br.com.desafio.produto.core.domain.Produto;
import br.com.desafio.produto.core.gateway.CarrinhoGateway;
import br.com.desafio.produto.core.gateway.PedidoGateway;
import br.com.desafio.produto.core.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdicionarProdutoNoCarrinhoUseCaseImplTest {

    @Mock
    private CarrinhoGateway carrinhoGateway;

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private AdicionarProdutoNoCarrinhoUseCaseImpl adicionarProdutoNoCarrinhoUseCase;

    @Test
    void deveAdicionarProdutoAoCarrinhoComSucesso() {
        // Arrange
        String produtoId = "1";
        String carrinhoId = "1";
        Produto produto = Produto.builder()
                .id(produtoId)
                .build();

        Carrinho carrinho = Carrinho.builder()
                .id(carrinhoId)
                .produtos(new ArrayList<>())
                .build();

        when(produtoGateway.findById(produtoId)).thenReturn(Optional.of(produto));
        when(carrinhoGateway.findById(carrinhoId)).thenReturn(Optional.of(carrinho));

        // Act
        adicionarProdutoNoCarrinhoUseCase.execute(carrinhoId, produtoId);

        // Assert
        verify(carrinhoGateway, times(1)).save(carrinho);
        assertTrue(carrinho.getProdutos().contains(produto));
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {
        // Arrange
        String produtoId = "1";
        String carrinhoId = "1";

        Carrinho carrinho = Carrinho.builder()
                .produtos(new ArrayList<>())
                .build();

        when(produtoGateway.findById(produtoId)).thenReturn(Optional.empty());
        when(carrinhoGateway.findById(carrinhoId)).thenReturn(Optional.of(carrinho));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
            adicionarProdutoNoCarrinhoUseCase.execute(carrinhoId, produtoId)
        );
    }
}