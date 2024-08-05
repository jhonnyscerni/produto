package br.com.desafio.produto.dataprovider.database.gateway.impl;

import br.com.desafio.produto.core.domain.Categoria;
import br.com.desafio.produto.core.domain.Produto;
import br.com.desafio.produto.core.gateway.ProdutoGateway;
import br.com.desafio.produto.dataprovider.database.entity.ProdutoEntity;
import br.com.desafio.produto.dataprovider.database.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProdutoGatewayImpl implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    @Override
    public List<Produto> findByCategoriaId(String categoriaId) {
        return produtoRepository.findByCategoriaId(categoriaId)
                .stream()
                .filter(entity -> entity.getQuantidade() > 0)
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> findById(String produtoId) {
        return produtoRepository.findById(produtoId).map(this::toDomain);
    }

    @Override
    public void incrementarQuantidade(List<Produto> produtos) {
        produtos.forEach(produto -> {
            ProdutoEntity entity = produtoRepository.findById(produto.getId()).orElseThrow();
            entity.setQuantidade(entity.getQuantidade() + produto.getQuantidade());
            produtoRepository.save(entity);
        });
    }

    private Produto toDomain(ProdutoEntity entity) {
        Categoria categoria = Categoria.builder()
                .id(entity.getCategoria().getId())
                .nome(entity.getCategoria().getNome())
                .descricao(entity.getCategoria().getDescricao())
                .build();

        return Produto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .quantidade(entity.getQuantidade())
                .valor(entity.getValor())
                .categoria(categoria)
                .build();
    }

}