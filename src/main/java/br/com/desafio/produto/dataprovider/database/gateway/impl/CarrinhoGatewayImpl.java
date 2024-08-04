package br.com.desafio.produto.dataprovider.database.gateway.impl;

import br.com.desafio.produto.core.common.exception.CarrinhoComProdutosException;
import br.com.desafio.produto.core.domain.Carrinho;
import br.com.desafio.produto.core.domain.Categoria;
import br.com.desafio.produto.core.domain.Produto;
import br.com.desafio.produto.core.gateway.CarrinhoGateway;
import br.com.desafio.produto.dataprovider.database.entity.CarrinhoEntity;
import br.com.desafio.produto.dataprovider.database.entity.CategoriaEntity;
import br.com.desafio.produto.dataprovider.database.entity.ProdutoEntity;
import br.com.desafio.produto.dataprovider.database.repository.CarrinhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CarrinhoGatewayImpl implements CarrinhoGateway {

    private final CarrinhoRepository carrinhoRepository;

    @Override
    public Carrinho save(Carrinho carrinho) {
        CarrinhoEntity entity = toEntity(carrinho);
        CarrinhoEntity savedEntity = carrinhoRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(String carrinhoId) {
        CarrinhoEntity carrinhoEntity = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new IllegalArgumentException("Carrinho not found"));

        if (carrinhoEntity.getProdutos() != null && !carrinhoEntity.getProdutos().isEmpty()) {
            throw new CarrinhoComProdutosException("Cannot delete carrinho with linked products");
        }

        carrinhoRepository.deleteById(carrinhoId);
    }

    @Override
    public Optional<Carrinho> findById(String carrinhoId) {
        return carrinhoRepository.findById(carrinhoId).map(this::toDomain);
    }

    private CarrinhoEntity toEntity(Carrinho carrinho) {
    return CarrinhoEntity.builder()
            .id(carrinho.getId())
            .produtos(carrinho.getProdutos().stream()
                    .map(produto -> ProdutoEntity.builder()
                            .id(produto.getId())
                            .nome(produto.getNome())
                            .descricao(produto.getDescricao())
                            .valor(produto.getValor())
                            .categoria(CategoriaEntity.builder()
                                    .id(produto.getCategoria().getId())
                                    .nome(produto.getCategoria().getNome())
                                    .descricao(produto.getCategoria().getDescricao())
                                    .build())
                            .build())
                    .collect(Collectors.toList()))
            .quantidade(carrinho.getQuantidade())
            .dataCriacao(carrinho.getDataCriacao())
            .dataAlteracao(carrinho.getDataAlteracao())
            .build();
}

    private Carrinho toDomain(CarrinhoEntity entity) {
    return Carrinho.builder()
            .id(entity.getId())
            .produtos(entity.getProdutos().stream()
                    .map(produtoEntity -> Produto.builder()
                            .id(produtoEntity.getId())
                            .nome(produtoEntity.getNome())
                            .descricao(produtoEntity.getDescricao())
                            .valor(produtoEntity.getValor())
                            .categoria(Categoria.builder()
                                    .id(produtoEntity.getCategoria().getId())
                                    .nome(produtoEntity.getCategoria().getNome())
                                    .descricao(produtoEntity.getCategoria().getDescricao())
                                    .build())
                            .build())
                    .collect(Collectors.toList()))
            .quantidade(entity.getQuantidade())
            .dataCriacao(entity.getDataCriacao())
            .dataAlteracao(entity.getDataAlteracao())
            .build();
}
}