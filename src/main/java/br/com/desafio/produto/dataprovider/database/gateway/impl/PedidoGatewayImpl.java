package br.com.desafio.produto.dataprovider.database.gateway.impl;

import br.com.desafio.produto.core.domain.Carrinho;
import br.com.desafio.produto.core.domain.Categoria;
import br.com.desafio.produto.core.domain.Pedido;
import br.com.desafio.produto.core.domain.Produto;
import br.com.desafio.produto.core.gateway.PedidoGateway;
import br.com.desafio.produto.dataprovider.database.entity.CarrinhoEntity;
import br.com.desafio.produto.dataprovider.database.entity.PedidoEntity;
import br.com.desafio.produto.dataprovider.database.entity.ProdutoEntity;
import br.com.desafio.produto.dataprovider.database.repository.CarrinhoRepository;
import br.com.desafio.produto.dataprovider.database.repository.PedidoRepository;
import br.com.desafio.produto.dataprovider.database.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PedidoGatewayImpl implements PedidoGateway {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public Pedido save(Pedido pedido) {
        PedidoEntity entity = toEntity(pedido);
        PedidoEntity savedEntity = pedidoRepository.save(entity);

        savedEntity.getCarrinho().getProdutos().forEach(produto -> {
            ProdutoEntity produtoEntity = produtoRepository.findById(produto.getId()).orElseThrow();
            produtoEntity.setQuantidade(produtoEntity.getQuantidade() - produto.getQuantidade());
            produtoRepository.save(produtoEntity);
        });

        return toDomain(savedEntity);
    }

    @Override
    public Optional<Pedido> findByCarrinhoId(String carrinhoId) {

        return pedidoRepository.findByCarrinhoId(carrinhoId)
                .stream()
                .map(this::toDomain)
                .findFirst();
    }

    private PedidoEntity toEntity(Pedido pedido) {
        return PedidoEntity.builder()
                .id(pedido.getId())
                .carrinho(CarrinhoEntity.builder()
                        .id(pedido.getCarrinho().getId())
                        .produtos(pedido.getCarrinho().getProdutos().stream()
                                .map(produto -> br.com.desafio.produto.dataprovider.database.entity.ProdutoEntity.builder()
                                        .id(produto.getId())
                                        .nome(produto.getNome())
                                        .descricao(produto.getDescricao())
                                        .valor(produto.getValor())
                                        .quantidade(produto.getQuantidade())
                                        .categoria(br.com.desafio.produto.dataprovider.database.entity.CategoriaEntity.builder()
                                                .id(produto.getCategoria().getId())
                                                .nome(produto.getCategoria().getNome())
                                                .descricao(produto.getCategoria().getDescricao())
                                                .build())
                                        .build())
                                .collect(Collectors.toList()))
                        .quantidade(pedido.getCarrinho().getQuantidade())
                        .dataCriacao(pedido.getCarrinho().getDataCriacao())
                        .dataAlteracao(pedido.getCarrinho().getDataAlteracao())
                        .build())
                .dataCriacao(pedido.getDataCriacao())
                .status(pedido.getStatus())
                .build();
    }

    private Pedido toDomain(PedidoEntity entity) {
        Carrinho carrinho = Carrinho.builder()
                .id(entity.getCarrinho().getId())
                .produtos(entity.getCarrinho().getProdutos().stream()
                        .map(produtoEntity -> Produto.builder()
                                .id(produtoEntity.getId())
                                .nome(produtoEntity.getNome())
                                .descricao(produtoEntity.getDescricao())
                                .valor(produtoEntity.getValor())
                                .quantidade(produtoEntity.getQuantidade())
                                .categoria(Categoria.builder()
                                        .id(produtoEntity.getCategoria().getId())
                                        .nome(produtoEntity.getCategoria().getNome())
                                        .descricao(produtoEntity.getCategoria().getDescricao())
                                        .build())
                                .build())
                        .collect(Collectors.toList()))
                .quantidade(entity.getCarrinho().getQuantidade())
                .dataCriacao(entity.getCarrinho().getDataCriacao())
                .dataAlteracao(entity.getCarrinho().getDataAlteracao())
                .build();

        return Pedido.builder()
                .id(entity.getId())
                .carrinho(carrinho)
                .dataCriacao(entity.getDataCriacao())
                .status(entity.getStatus())
                .build();
    }
}