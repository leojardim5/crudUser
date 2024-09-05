package com.leonardojardim.crudLojaLivro.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardojardim.crudLojaLivro.model.User;
import com.leonardojardim.crudLojaLivro.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController implements ControllerInterface<User, Long> {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or #id == principal.id")  // Apenas o usuário ou um admin pode acessar
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> getAll() {
        // Obter o contexto de segurança atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Imprimir as autoridades do usuário, mesmo que a autorização seja negada
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            System.out.println("Authorities do usuário: " + authorities);
        } else {
            System.out.println("Nenhuma autenticação disponível.");
        }

        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User newUser = userService.register(user);
        return ResponseEntity.ok(newUser);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")  // Apenas o usuário ou um admin pode atualizar os dados
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User entityDetails) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(entityDetails.getEmail());
            user.setNome(entityDetails.getNome());
            user.setTelefone(entityDetails.getTelefone());
            user.setPassword(passwordEncoder.encode(entityDetails.getPassword()));
            User updatedUser = userService.update(user);
            return ResponseEntity.ok(updatedUser);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")  // Apenas o usuário ou um admin pode deletar
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
