package com.example.gac.service;

import com.example.gac.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    /**
     * Función que crea un usuario.
     * @param client
     * @return
     */
    Optional<Client> create(Client client);

    /**
     * Función que actualiza un cliente.
     * @param client
     * @return
     */
    Optional<Client> update(Client client);

    /**
     * Función que elimina un usuario.
     * @param client
     * @return booleano a false si no se ha podido crear o un booleano a true si se ha creado con éxito.
     */
    Optional<Client> delete(Client client);

    /**
     * Busca cliente por id.
     * @param id
     * @return
     */
    Optional<Client> findOne(Integer id);

    /**
     * Recupera todos los clientes que se encuentran en la base de datos.
     * @return
     */
    List<Client> findAll();
}
