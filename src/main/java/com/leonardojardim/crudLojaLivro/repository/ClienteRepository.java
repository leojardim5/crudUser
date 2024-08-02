package com.leonardojardim.crudLojaLivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardojardim.crudLojaLivro.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
