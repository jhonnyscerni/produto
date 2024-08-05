package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.domain.Categoria;
import br.com.desafio.produto.core.domain.Produto;
import br.com.desafio.produto.core.gateway.CategoriaGateway;
import br.com.desafio.produto.core.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscaProdutoCategoriaUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private CategoriaGateway categoriaGateway;

    @InjectMocks
    private BuscaProdutoCategoriaUseCaseImpl buscaProdutoCategoriaUseCase;

    @Test
    void deveRetornarProdutosQuandoCategoriaIdExistir() {
        // Arrange
        String categoriaId = "1";
        Produto produto = new Produto();
        List<Produto> produtos = Collections.singletonList(produto);
        when(categoriaGateway.findById(categoriaId)).thenReturn(Mockito.mock(Categoria.class));
        when(produtoGateway.findByCategoriaId(categoriaId)).thenReturn(produtos);

        // Act
        List<Produto> resultado = buscaProdutoCategoriaUseCase.execute(categoriaId);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(produto, resultado.get(0));
    }

}