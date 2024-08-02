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

import com.leonardojardim.crudLojaLivro.model.Cliente;
import com.leonardojardim.crudLojaLivro.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController implements ControllerInterface<Cliente, Long> {

    @Autowired
    private ClienteService clienteService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        Optional<Cliente> optionalCliente = clienteService.findById(id);

        if (optionalCliente.isPresent()) {
            return ResponseEntity.ok(optionalCliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {

        Cliente clienteNovo = clienteService.save(cliente);
        return ResponseEntity.ok(clienteNovo);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente entityDetails) {

        Optional<Cliente> optionalCliente = clienteService.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setEmail(entityDetails.getEmail());
            cliente.setNome(entityDetails.getNome());
            cliente.setTelefone(entityDetails.getTelefone());
            Cliente updatedCliente = clienteService.save(cliente);
            return ResponseEntity.ok(updatedCliente);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Cliente> optionalCliente = clienteService.findById(id);

        if (optionalCliente.isPresent()) {
            clienteService.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }

    @Override
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clientes = clienteService.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);

    }

}
