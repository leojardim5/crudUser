package com.leonardojardim.crudLojaLivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardojardim.crudLojaLivro.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
