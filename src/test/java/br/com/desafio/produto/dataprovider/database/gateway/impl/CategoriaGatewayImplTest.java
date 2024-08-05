package br.com.desafio.produto.dataprovider.database.gateway.impl;

import br.com.desafio.produto.core.common.exception.CategoriaNaoEncontradaException;
import br.com.desafio.produto.core.domain.Categoria;
import br.com.desafio.produto.dataprovider.database.entity.CategoriaEntity;
import br.com.desafio.produto.dataprovider.database.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaGatewayImplTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaGatewayImpl categoriaGateway;

    @Test
    void deveEncontrarCategoriaPorIdComSucesso() {
        // Arrange
        String categoriaId = "1";
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(categoriaId);
        categoriaEntity.setNome("Categoria Teste");
        categoriaEntity.setDescricao("Descrição Teste");

        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoriaEntity));

        // Act
        Categoria result = categoriaGateway.findById(categoriaId);

        // Assert
        assertNotNull(result);
        assertEquals(categoriaId, result.getId());
        assertEquals("Categoria Teste", result.getNome());
        assertEquals("Descrição Teste", result.getDescricao());
        verify(categoriaRepository, times(1)).findById(categoriaId);
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoForEncontrada() {
        // Arrange
        String categoriaId = "1";
        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaGateway.findById(categoriaId));
        verify(categoriaRepository, times(1)).findById(categoriaId);
    }
}