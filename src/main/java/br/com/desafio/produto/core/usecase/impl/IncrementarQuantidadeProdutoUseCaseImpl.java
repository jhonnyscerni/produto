package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.domain.Produto;
import br.com.desafio.produto.core.gateway.ProdutoGateway;
import br.com.desafio.produto.core.usecase.IncrementarQuantidadeProdutoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncrementarQuantidadeProdutoUseCaseImpl implements IncrementarQuantidadeProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public void incrementarQuantidade(List<Produto> produtos) {
        produtoGateway.incrementarQuantidade(produtos);
    }
}