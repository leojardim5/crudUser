package com.leonardojardim.crudLojaLivro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardojardim.crudLojaLivro.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
