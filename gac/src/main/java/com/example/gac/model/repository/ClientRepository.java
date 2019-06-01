package com.example.gac.model.repository;

import com.example.gac.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAllByName(String name);

    List<Client> findAllByDni(String dni);

}
