package com.leonardojardim.crudLojaLivro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardojardim.crudLojaLivro.model.Livro;
import com.leonardojardim.crudLojaLivro.repository.LivroRepository;

@Service
public class LivroService implements ServiceInterface<Livro> {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public List<Livro> findAll() {
        return (List<Livro>) livroRepository.findAll();
    }

    @Override
    public Optional<Livro> findById(long id) {
        return livroRepository.findById(id);
    }

    @Override
    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public void deleteById(long id) {
        livroRepository.deleteById(id);
    }
}
