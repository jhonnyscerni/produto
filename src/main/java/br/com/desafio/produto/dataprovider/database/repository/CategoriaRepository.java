package br.com.desafio.produto.dataprovider.database.repository;

import br.com.desafio.produto.dataprovider.database.entity.CategoriaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends MongoRepository<CategoriaEntity, String> {
}