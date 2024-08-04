package br.com.desafio.produto.dataprovider.database.repository;

import br.com.desafio.produto.dataprovider.database.entity.CarrinhoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends MongoRepository<CarrinhoEntity, String> {
}