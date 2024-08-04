package br.com.desafio.produto.core.gateway;

import br.com.desafio.produto.core.domain.Carrinho;

import java.util.Optional;

public interface CarrinhoGateway {
    Carrinho save(Carrinho carrinho);
    void delete(String carrinhoId);
    Optional<Carrinho> findById(String carrinhoId);
}