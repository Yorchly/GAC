package com.example.gac.service;

import com.example.gac.model.Client;
import com.example.gac.model.Rent;
import com.example.gac.model.dto.ClientDto;
import com.example.gac.model.dto.RentDto;
import com.example.gac.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired private ClientRepository repository;

    @Autowired private RentServiceImpl rentService;

    @Override
    public Optional<Client> create(Client client)
    {
        if(repository.findById(client.getId()).isPresent())
            return Optional.empty();
        else
            return Optional.ofNullable(repository.save(client));
    }


    @Override
    public ResponseEntity<ClientDto> update(Client client) {
        if(repository.findById(client.getId()).isPresent())
        {
            repository.save(client);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<ClientDto> delete(Client client) {
        if(repository.findById(client.getId()).isPresent())
        {
            repository.delete(client);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Override
    public Optional<Client> findOne(Integer id) {
        return repository.findById(id);
    }


    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Client> findAllByName(String name) {
        return repository.findAllByName(name);
    }

    @Override
    public List<Client> findAllByDni(String dni) {
        return repository.findAllByDni(dni);
    }

}
