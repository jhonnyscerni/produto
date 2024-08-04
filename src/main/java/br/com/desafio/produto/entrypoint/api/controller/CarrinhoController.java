package br.com.desafio.produto.entrypoint.api.controller;

import br.com.desafio.produto.core.usecase.CriarCarrinhoUseCase;
import br.com.desafio.produto.core.usecase.ExcluirCarrinhoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.produto.core.domain.Carrinho;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CriarCarrinhoUseCase criarCarrinhoUseCase;
    private final ExcluirCarrinhoUseCase excluirCarrinhoUseCase;

    @PostMapping
    public Mono<ResponseEntity<Carrinho>> criarCarrinho(@RequestBody Carrinho carrinho) {
        return Mono.fromCallable(() -> criarCarrinhoUseCase.execute(carrinho))
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{carrinhoId}")
    public Mono<ResponseEntity<Void>> excluirCarrinho(@PathVariable String carrinhoId) {
        return Mono.fromRunnable(() -> excluirCarrinhoUseCase.execute(carrinhoId))
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}