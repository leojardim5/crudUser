package com.leonardojardim.crudLojaLivro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardojardim.crudLojaLivro.model.Carro;
import com.leonardojardim.crudLojaLivro.repository.CarroRepository;

@Service
public class CarroService implements ServiceInterface<Carro> {

    @Autowired
    private CarroRepository carroRepository;

    @Override
    public Carro save(Carro entity) {
        Carro carro = carroRepository.save(entity);
        return carro;

    }

    @Override
    public List<Carro> findAll() {
        List<Carro> carros = carroRepository.findAll();
        return carros;
    }

    @Override
    public Optional<Carro> findById(long id) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        return carroOptional;
    }

    @Override
    public void deleteById(long id) {
        carroRepository.deleteById(id);
    }

}
