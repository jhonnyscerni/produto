package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.domain.Carrinho;
import br.com.desafio.produto.core.gateway.CarrinhoGateway;
import br.com.desafio.produto.core.usecase.CriarCarrinhoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarCarrinhoUseCaseImpl implements CriarCarrinhoUseCase {

    private final CarrinhoGateway carrinhoGateway;

    @Override
    public Carrinho execute(Carrinho carrinho) {
        return carrinhoGateway.save(carrinho);
    }
}