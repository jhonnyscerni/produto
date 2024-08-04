package br.com.desafio.produto.dataprovider.database.repository;

import br.com.desafio.produto.dataprovider.database.entity.ProdutoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends MongoRepository<ProdutoEntity, String> {
    List<ProdutoEntity> findByCategoriaId(String categoriaId);
}