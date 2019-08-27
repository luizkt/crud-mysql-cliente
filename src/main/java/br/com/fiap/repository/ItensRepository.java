package br.com.fiap.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.fiap.entity.Itens;

public interface ItensRepository extends CrudRepository<Itens, Integer> {
}
