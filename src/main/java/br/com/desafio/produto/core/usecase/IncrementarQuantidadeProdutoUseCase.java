package br.com.desafio.produto.core.usecase;

import br.com.desafio.produto.core.domain.Produto;

import java.util.List;

public interface IncrementarQuantidadeProdutoUseCase {

    void incrementarQuantidade(List<Produto> produtos);
}