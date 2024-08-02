package com.leonardojardim.crudLojaLivro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardojardim.crudLojaLivro.model.Cliente;
import com.leonardojardim.crudLojaLivro.repository.ClienteRepository;

@Service
public class ClienteService implements ServiceInterface<Cliente> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        clienteRepository.deleteById(id);
    }

}
