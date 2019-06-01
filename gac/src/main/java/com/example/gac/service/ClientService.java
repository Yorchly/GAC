package com.example.gac.service;

import com.example.gac.model.Client;
import com.example.gac.model.Rent;
import com.example.gac.model.dto.ClientDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    /**
     * Función que crea un elemento del tipo Client.
     * @param client
     * @return
     */
    Optional<Client> create(Client client);

    /**
     * Función que actualiza un elemento del tipo Client.
     * @param client
     * @return
     */
     ResponseEntity<ClientDto> update(Client client);

    /**
     * Función que elimina un elemento del tipo Client.
     * @param client
     * @return
     */
     ResponseEntity<ClientDto> delete(Client client);

    /**
     * Busca un elemento del tipo Client por id.
     * @param id
     * @return
     */
    Optional<Client> findOne(Integer id);

    /**
     * Recupera todos los elementos del tipo Client que se encuentran en la base de datos.
     * @return
     */
    List<Client> findAll();


    /**
     * Encuentra todos los clientes que coincidan con el nombre pasado como argumento.
     * @param name
     * @return
     */
    List<Client> findAllByName(String name);


    /**
     * Suponiendo que el DNI no es único, encuentra la lista de todos los usuarios con el mismo DNI.
     * @param dni
     * @return
     */
    List<Client> findAllByDni(String dni);

}
