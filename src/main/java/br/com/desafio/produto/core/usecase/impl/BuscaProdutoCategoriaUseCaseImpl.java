package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.domain.Produto;
import br.com.desafio.produto.core.gateway.CategoriaGateway;
import br.com.desafio.produto.core.gateway.ProdutoGateway;
import br.com.desafio.produto.core.usecase.BuscaProdutosCategoriaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscaProdutoCategoriaUseCaseImpl implements BuscaProdutosCategoriaUseCase {

    private final ProdutoGateway produtoGateway;
    private final CategoriaGateway categoriaGateway;

    @Override
    public List<Produto> execute(String categoriaId) {
        categoriaGateway.findById(categoriaId);
        return produtoGateway.findByCategoriaId(categoriaId);
    }
}
