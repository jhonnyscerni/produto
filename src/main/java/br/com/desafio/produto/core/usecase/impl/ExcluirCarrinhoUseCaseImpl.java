package br.com.desafio.produto.core.usecase.impl;

import br.com.desafio.produto.core.gateway.CarrinhoGateway;
import br.com.desafio.produto.core.usecase.ExcluirCarrinhoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirCarrinhoUseCaseImpl implements ExcluirCarrinhoUseCase {

    private final CarrinhoGateway carrinhoGateway;

    @Override
    public void execute(String carrinhoId) {
        carrinhoGateway.delete(carrinhoId);
    }
}