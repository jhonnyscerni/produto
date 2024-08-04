package br.com.desafio.produto.dataprovider.database.config;

import br.com.desafio.produto.dataprovider.database.entity.CarrinhoEntity;
import br.com.desafio.produto.dataprovider.database.entity.CategoriaEntity;
import br.com.desafio.produto.dataprovider.database.entity.PedidoEntity;
import br.com.desafio.produto.dataprovider.database.entity.ProdutoEntity;
import br.com.desafio.produto.dataprovider.database.repository.CarrinhoRepository;
import br.com.desafio.produto.dataprovider.database.repository.CategoriaRepository;
import br.com.desafio.produto.dataprovider.database.repository.PedidoRepository;
import br.com.desafio.produto.dataprovider.database.repository.ProdutoRepository;
import br.com.desafio.produto.core.domain.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final PedidoRepository pedidoRepository;

    @Bean
    public ApplicationRunner loadFakeData() {
        return args -> {
            CategoriaEntity categoria1 = CategoriaEntity.builder()
                    .id("1")
                    .nome("Eletrônicos")
                    .descricao("Categoria de produtos eletrônicos")
                    .build();

            CategoriaEntity categoria2 = CategoriaEntity.builder()
                    .id("2")
                    .nome("Livros")
                    .descricao("Categoria de livros")
                    .build();

            CategoriaEntity categoria3 = CategoriaEntity.builder()
                    .id("3")
                    .nome("Roupas")
                    .descricao("Categoria de roupas")
                    .build();

            categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3));

            ProdutoEntity produto1 = ProdutoEntity.builder()
                    .id("1")
                    .nome("Smartphone")
                    .descricao("Smartphone de última geração")
                    .valor(new BigDecimal("1999.99"))
                    .categoria(categoria1)
                    .build();

            ProdutoEntity produto2 = ProdutoEntity.builder()
                    .id("2")
                    .nome("Livro de Java")
                    .descricao("Livro sobre programação em Java")
                    .valor(new BigDecimal("59.99"))
                    .categoria(categoria2)
                    .build();

            ProdutoEntity produto3 = ProdutoEntity.builder()
                    .id("3")
                    .nome("Camiseta")
                    .descricao("Camiseta de algodão")
                    .valor(new BigDecimal("29.99"))
                    .categoria(categoria3)
                    .build();

            ProdutoEntity produto4 = ProdutoEntity.builder()
                    .id("4")
                    .nome("Camiseta 2")
                    .descricao("Camiseta de algodão 2")
                    .valor(new BigDecimal("29.99"))
                    .categoria(categoria3)
                    .build();

            produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4));

            CarrinhoEntity carrinho1 = CarrinhoEntity.builder()
                    .id("1")
                    .produtos(Arrays.asList(produto1, produto2))
                    .quantidade(2)
                    .dataCriacao(LocalDateTime.now())
                    .dataAlteracao(LocalDateTime.now())
                    .build();

            CarrinhoEntity carrinho2 = CarrinhoEntity.builder()
                    .id("2")
                    .produtos(Arrays.asList(produto3, produto4))
                    .quantidade(2)
                    .dataCriacao(LocalDateTime.now())
                    .dataAlteracao(LocalDateTime.now())
                    .build();

            CarrinhoEntity carrinho3 = CarrinhoEntity.builder()
                    .id("3")
                    .produtos(Arrays.asList(produto1, produto4))
                    .quantidade(2)
                    .dataCriacao(LocalDateTime.now())
                    .dataAlteracao(LocalDateTime.now())
                    .build();

            carrinhoRepository.saveAll(Arrays.asList(carrinho1, carrinho2, carrinho3));

            PedidoEntity pedido1 = PedidoEntity.builder()
                    .id("1")
                    .carrinho(carrinho1)
                    .dataCriacao(LocalDateTime.now())
                    .status(Status.DONE)
                    .build();

            PedidoEntity pedido2 = PedidoEntity.builder()
                    .id("2")
                    .carrinho(carrinho2)
                    .dataCriacao(LocalDateTime.now())
                    .status(Status.ERROR_PAYMENT)
                    .build();

            PedidoEntity pedido3 = PedidoEntity.builder()
                    .id("3")
                    .carrinho(carrinho3)
                    .dataCriacao(LocalDateTime.now())
                    .status(Status.WAIT_PAYMENT)
                    .build();

            pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));
        };
    }
}