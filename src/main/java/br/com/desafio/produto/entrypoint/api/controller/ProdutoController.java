package br.com.desafio.produto.entrypoint.api.controller;

import br.com.desafio.produto.core.domain.Produto;
import br.com.desafio.produto.core.usecase.AdicionarProdutoNoCarrinhoUseCase;
import br.com.desafio.produto.core.usecase.RemoverProdutoDoCarrinhoUseCase;
import br.com.desafio.produto.core.usecase.impl.BuscaProdutoCategoriaUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final BuscaProdutoCategoriaUseCaseImpl buscaProdutoCategoriaUseCase;
    private final AdicionarProdutoNoCarrinhoUseCase adicionarProdutoNoCarrinhoUseCase;
    private final RemoverProdutoDoCarrinhoUseCase removerProdutoDoCarrinhoUseCase;

    @GetMapping("/categoria/{categoriaId}")
    public Mono<ResponseEntity<List<Produto>>> buscarPorCategoriaId(@PathVariable String categoriaId) {
        return Mono.fromCallable(() -> buscaProdutoCategoriaUseCase.execute(categoriaId))
                .map(ResponseEntity::ok);
    }

    @PostMapping("/{carrinhoId}/{produtoId}")
    public Mono<ResponseEntity<Void>> adicionarAoCarrinho(@PathVariable String carrinhoId, @PathVariable String produtoId) {
        return Mono.fromRunnable(() -> adicionarProdutoNoCarrinhoUseCase.execute(carrinhoId, produtoId))
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @DeleteMapping("/{carrinhoId}/{produtoId}")
    public Mono<ResponseEntity<Void>> removerDoCarrinho(@PathVariable String carrinhoId, @PathVariable String produtoId) {
        return Mono.fromRunnable(() -> removerProdutoDoCarrinhoUseCase.execute(carrinhoId, produtoId))
                .then(Mono.just(ResponseEntity.noContent().build()));
    }


}