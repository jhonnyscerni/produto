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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoverProdutoDoCarrinhoUseCaseImplTest {

    @Mock
    private CarrinhoGateway carrinhoGateway;

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private RemoverProdutoDoCarrinhoUseCaseImpl removerProdutoDoCarrinhoUseCase;

    @Test
    void deveRemoverProdutoDoCarrinhoComSucesso() {
        // Arrange
        String carrinhoId = "1";
        String produtoId = "1";
        Carrinho carrinho = Carrinho.builder()
                .produtos(new ArrayList<>())
                .build();
        Produto produto = new Produto();
        produto.setId(produtoId);
        carrinho.getProdutos().add(produto);

        when(carrinhoGateway.findById(carrinhoId)).thenReturn(Optional.of(carrinho));
        when(produtoGateway.findById(produtoId)).thenReturn(Optional.of(produto));
        when(pedidoGateway.findByCarrinhoId(carrinhoId)).thenReturn(Optional.empty());

        // Act
        removerProdutoDoCarrinhoUseCase.execute(carrinhoId, produtoId);

        // Assert
        verify(carrinhoGateway, times(1)).findById(carrinhoId);
        verify(produtoGateway, times(1)).findById(produtoId);
        verify(carrinhoGateway, times(1)).save(carrinho);
        verify(pedidoGateway, times(1)).findByCarrinhoId(carrinhoId);
    }

    @Test
    void deveLancarExcecaoQuandoCarrinhoNaoForEncontrado() {
        // Arrange
        String carrinhoId = "1";
        String produtoId = "1";

        when(carrinhoGateway.findById(carrinhoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
            removerProdutoDoCarrinhoUseCase.execute(carrinhoId, produtoId)
        );
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoForEncontrado() {
        // Arrange
        String carrinhoId = "1";
        String produtoId = "1";
        Carrinho carrinho = Carrinho.builder()
                .produtos(new ArrayList<>())
                .build();

        when(carrinhoGateway.findById(carrinhoId)).thenReturn(Optional.of(carrinho));
        when(produtoGateway.findById(produtoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
            removerProdutoDoCarrinhoUseCase.execute(carrinhoId, produtoId)
        );
    }
}