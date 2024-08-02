package com.leonardojardim.crudLojaLivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardojardim.crudLojaLivro.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

}
