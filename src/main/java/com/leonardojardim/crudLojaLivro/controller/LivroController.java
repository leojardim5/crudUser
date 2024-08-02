package com.leonardojardim.crudLojaLivro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardojardim.crudLojaLivro.model.Livro;
import com.leonardojardim.crudLojaLivro.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController implements ControllerInterface<Livro, Long> {

    @Autowired
    private LivroService livroService;

    @Override
    @GetMapping
    public ResponseEntity<List<Livro>> getAll() {
        List<Livro> livros = livroService.findAll();
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Livro> getById(@PathVariable Long id) {
        Optional<Livro> livro = livroService.findById(id);

        if (livro.isPresent()) {
            return ResponseEntity.ok(livro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Livro> create(@RequestBody Livro livro) {
        Livro livroSalvo = livroService.save(livro);
        return ResponseEntity.ok(livroSalvo);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro livroDetails) {
        Optional<Livro> optionalLivro = livroService.findById(id);

        if (optionalLivro.isPresent()) {
            Livro livro = optionalLivro.get();
            livro.setTitulo(livroDetails.getTitulo());
            livro.setAutor(livroDetails.getAutor());
            livro.setPreco(livroDetails.getPreco());
            final Livro updatedLivro = livroService.save(livro);
            return ResponseEntity.ok(updatedLivro);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Livro> optionalLivro = livroService.findById(id);

        if (optionalLivro.isPresent()) {
            livroService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
