package com.example.gac.service;

import com.example.gac.model.Client;
import com.example.gac.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired private ClientRepository repository;

    @Override
    public Optional<Client> create(Client client) {
        return Optional.ofNullable(repository.save(client));
    }

    @Override
    public Optional<Client> update(Client client) {
        if(repository.findById(client.getId()).isPresent())
            return Optional.ofNullable(repository.save(client));
        else
            return Optional.empty();
    }

    @Override
    public Optional<Client> delete(Client client) {
        if(repository.findById(client.getId()).isPresent())
        {
            repository.delete(client);
            return Optional.of(client);
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<Client> findOne(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }
}
