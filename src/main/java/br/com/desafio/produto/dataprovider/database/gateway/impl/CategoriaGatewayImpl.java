package br.com.desafio.produto.dataprovider.database.gateway.impl;

import br.com.desafio.produto.core.common.exception.CategoriaNaoEncontradaException;
import br.com.desafio.produto.core.domain.Categoria;
import br.com.desafio.produto.core.gateway.CategoriaGateway;
import br.com.desafio.produto.dataprovider.database.repository.CategoriaRepository;
import br.com.desafio.produto.dataprovider.database.entity.CategoriaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoriaGatewayImpl implements CategoriaGateway {

    private final CategoriaRepository categoriaRepository;

    @Override
    public Categoria findById(String categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .map(this::toDomain)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(categoriaId));

    }

    private Categoria toDomain(CategoriaEntity entity) {
        return Categoria.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .build();
    }
}