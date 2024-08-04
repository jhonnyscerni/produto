package br.com.desafio.produto.core.gateway;

import br.com.desafio.produto.core.domain.Categoria;

public interface CategoriaGateway {

    Categoria findById(String categoriaId);
}
