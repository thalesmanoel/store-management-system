package com.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.store.backend.entities.Client;
import com.store.backend.service.ClientService;

@RestController
@RequestMapping(value = "/client")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/get")
    public ResponseEntity<List<Client>> findAll() {
        List<Client> list = clientService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/get/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") Long id) {
        Client obj = clientService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<Client> registerClient(@RequestBody Client client) {
        Client createdClient = clientService.registerClient(client);
        return ResponseEntity.ok(createdClient);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") Long id, @RequestBody Client obj) {
        obj = clientService.updateClient(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
