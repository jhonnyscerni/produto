package br.com.desafio.produto.entrypoint.api.controller;

import br.com.desafio.produto.core.domain.Pedido;
import br.com.desafio.produto.core.usecase.BuscaPedidoPorCarrinhoIdUseCase;
import br.com.desafio.produto.core.usecase.CriarPedidoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final BuscaPedidoPorCarrinhoIdUseCase buscaPedidoPorCarrinhoIdUseCase;

    @PostMapping
    public Mono<Pedido> criarPedido(@RequestBody Pedido pedido) {
        return Mono.fromCallable(() -> criarPedidoUseCase.execute(pedido));
    }

    @GetMapping("/carrinho/{carrinhoId}")
    public Mono<ResponseEntity<Pedido>> buscarPorCarrinhoId(@PathVariable String carrinhoId) {
        return Mono.fromCallable(() -> buscaPedidoPorCarrinhoIdUseCase.execute(carrinhoId))
                .map(ResponseEntity::ok);
    }
}