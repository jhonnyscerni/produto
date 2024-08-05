package br.com.desafio.produto.dataprovider.database.gateway.impl;

import br.com.desafio.produto.core.domain.Produto;
import br.com.desafio.produto.dataprovider.database.entity.CategoriaEntity;
import br.com.desafio.produto.dataprovider.database.entity.ProdutoEntity;
import br.com.desafio.produto.dataprovider.database.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoGatewayImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoGatewayImpl produtoGateway;

    @Test
    void deveEncontrarProdutosPorCategoriaIdComSucesso() {
        // Arrange
        String categoriaId = "1";
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(categoriaId);
        categoriaEntity.setNome("Categoria Teste");

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId("1");
        produtoEntity.setNome("Produto Teste");
        produtoEntity.setQuantidade(5);
        produtoEntity.setCategoria(categoriaEntity);

        when(produtoRepository.findByCategoriaId(categoriaId)).thenReturn(List.of(produtoEntity));

        // Act
        List<Produto> result = produtoGateway.findByCategoriaId(categoriaId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Produto Teste", result.get(0).getNome());
        verify(produtoRepository, times(1)).findByCategoriaId(categoriaId);
    }

    @Test
    void deveIncrementarQuantidadeDosProdutosComSucesso() {
        // Arrange
        Produto produto = new Produto();
        produto.setId("1");
        produto.setQuantidade(2);

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId("1");
        produtoEntity.setQuantidade(5);

        when(produtoRepository.findById("1")).thenReturn(Optional.of(produtoEntity));

        // Act
        produtoGateway.incrementarQuantidade(List.of(produto));

        // Assert
        assertEquals(7, produtoEntity.getQuantidade());
        verify(produtoRepository, times(1)).save(produtoEntity);
    }
}