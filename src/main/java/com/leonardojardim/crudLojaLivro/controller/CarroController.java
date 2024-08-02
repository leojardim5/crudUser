package com.leonardojardim.crudLojaLivro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leonardojardim.crudLojaLivro.model.Carro;
import com.leonardojardim.crudLojaLivro.service.CarroService;

@Controller
@RequestMapping("/carros")
public class CarroController implements ControllerInterface<Carro, Long> {

    @Autowired
    private CarroService carroService;

    @Override
    @GetMapping
    public ResponseEntity<List<Carro>> getAll() {
        List<Carro> carros = carroService.findAll();
        return new ResponseEntity<>(carros, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Carro> getById(@PathVariable Long id) {
        Optional<Carro> carroOptional = carroService.findById(id);
        if (carroOptional.isPresent()) {
            return ResponseEntity.ok(carroOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Carro> create(@RequestBody Carro entity) {
        Carro carro = carroService.save(entity);
        return ResponseEntity.ok(carro);

    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Carro> update(@PathVariable Long id, @RequestBody Carro entityDetails) {
        Optional<Carro> carroOptional = carroService.findById(id);

        if (carroOptional.isPresent()) {
            Carro carro = carroOptional.get();
            carro.setAno(entityDetails.getAno());
            carro.setCor(entityDetails.getCor());
            carro.setMarca(entityDetails.getMarca());
            carro.setPlaca(entityDetails.getPlaca());
            Carro savedCarro = carroService.save(carro);
            return ResponseEntity.ok(savedCarro);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Carro> carroOptional = carroService.findById(id);
        if (carroOptional.isPresent()) {
            carroService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
