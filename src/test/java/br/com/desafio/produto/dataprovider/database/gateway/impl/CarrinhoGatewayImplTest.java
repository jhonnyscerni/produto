package br.com.desafio.produto.dataprovider.database.gateway.impl;

import br.com.desafio.produto.core.common.exception.CarrinhoComProdutosException;
import br.com.desafio.produto.core.domain.Carrinho;
import br.com.desafio.produto.dataprovider.database.entity.CarrinhoEntity;
import br.com.desafio.produto.dataprovider.database.entity.ProdutoEntity;
import br.com.desafio.produto.dataprovider.database.repository.CarrinhoRepository;
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
class CarrinhoGatewayImplTest {

    @Mock
    private CarrinhoRepository carrinhoRepository;

    @InjectMocks
    private CarrinhoGatewayImpl carrinhoGateway;

    @Test
    void deveSalvarCarrinhoComSucesso() {
        // Arrange
        Carrinho carrinho = new Carrinho();
        carrinho.setProdutos(new ArrayList<>()); // Inicializa a lista de produtos
        CarrinhoEntity carrinhoEntity = new CarrinhoEntity();
        carrinhoEntity.setProdutos(new ArrayList<>());
        when(carrinhoRepository.save(any(CarrinhoEntity.class))).thenReturn(carrinhoEntity);

        // Act
        Carrinho result = carrinhoGateway.save(carrinho);

        // Assert
        assertNotNull(result);
        verify(carrinhoRepository, times(1)).save(any(CarrinhoEntity.class));
    }

    @Test
    void deveDeletarCarrinhoComSucesso() {
        // Arrange
        String carrinhoId = "1";
        CarrinhoEntity carrinhoEntity = new CarrinhoEntity();
        carrinhoEntity.setProdutos(new ArrayList<>()); // Inicializa a lista de produtos
        when(carrinhoRepository.findById(carrinhoId)).thenReturn(Optional.of(carrinhoEntity));

        // Act
        carrinhoGateway.delete(carrinhoId);

        // Assert
        verify(carrinhoRepository, times(1)).deleteById(carrinhoId);
    }

    @Test
    void deveLancarExcecaoAoDeletarCarrinhoComProdutos() {
        // Arrange
        String carrinhoId = "1";
        CarrinhoEntity carrinhoEntity = new CarrinhoEntity();
        carrinhoEntity.setProdutos(new ArrayList<>()); // Inicializa a lista de produtos
        carrinhoEntity.getProdutos().add(new ProdutoEntity());
        when(carrinhoRepository.findById(carrinhoId)).thenReturn(Optional.of(carrinhoEntity));

        // Act & Assert
        assertThrows(CarrinhoComProdutosException.class, () -> carrinhoGateway.delete(carrinhoId));
    }

    @Test
    void deveEncontrarCarrinhoPorIdComSucesso() {
        // Arrange
        String carrinhoId = "1";
        CarrinhoEntity carrinhoEntity = new CarrinhoEntity();
        carrinhoEntity.setProdutos(new ArrayList<>()); // Inicializa a lista de produtos
        when(carrinhoRepository.findById(carrinhoId)).thenReturn(Optional.of(carrinhoEntity));

        // Act
        Optional<Carrinho> result = carrinhoGateway.findById(carrinhoId);

        // Assert
        assertTrue(result.isPresent());
        verify(carrinhoRepository, times(1)).findById(carrinhoId);
    }

    @Test
    void deveRetornarVazioQuandoCarrinhoNaoForEncontrado() {
        // Arrange
        String carrinhoId = "1";
        when(carrinhoRepository.findById(carrinhoId)).thenReturn(Optional.empty());

        // Act
        Optional<Carrinho> result = carrinhoGateway.findById(carrinhoId);

        // Assert
        assertFalse(result.isPresent());
        verify(carrinhoRepository, times(1)).findById(carrinhoId);
    }
}