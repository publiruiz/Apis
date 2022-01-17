package com.platzi.platzimarket.persistence.CRUD;

import com.platzi.platzimarket.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    List<Compra> findByIdUsuario(int idUsuario);
}
