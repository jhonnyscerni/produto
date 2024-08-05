package br.com.desafio.produto.core.gateway;

import br.com.desafio.produto.core.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {

    List<Produto> findByCategoriaId(String categoriaId);

    Optional<Produto> findById(String produtoId);

    void incrementarQuantidade(List<Produto> produtos);
}
