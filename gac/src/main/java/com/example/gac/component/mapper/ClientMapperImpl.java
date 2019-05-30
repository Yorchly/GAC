package com.example.gac.component.mapper;

import com.example.gac.model.Client;
import com.example.gac.model.dto.ClientDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClientMapperImpl implements MapperComponent<ClientDto, Client> {

    @Override
    public Optional<ClientDto> mapDaoToDto(Client elementS)
    {
        if(Optional.ofNullable(elementS).isPresent())
            return Optional.of(new ClientDto(elementS.getId(), elementS.getDni(), elementS.getName()));
        else
            return Optional.empty();
    }

    @Override
    public Optional<Client> mapDtoToDao(ClientDto elementT)
    {
        if(Optional.ofNullable(elementT).isPresent())
            return Optional.of(new Client(elementT.getId(), elementT.getDni(), elementT.getName()));
        else
            return Optional.empty();
    }

    @Override
    public List<ClientDto> mapDaoToDto(List<Client> elementS)
    {
        List<ClientDto> list = new ArrayList<>();

        // Comprueba si la lista está vacía.
        if(!elementS.isEmpty())
            for(Client c: elementS)
                // Si la lista no está vacía y el elemento se ha podido mapear sin problemas, lo introduce en la lista
                // que se va a devolver.
                if(mapDaoToDto(c).isPresent())
                    list.add(mapDaoToDto(c).get());

        return list;
    }

    @Override
    public List<Client> mapDtoToDao(List<ClientDto> elementT)
    {
        List<Client> list = new ArrayList<>();

        if(!elementT.isEmpty())
            for(ClientDto client: elementT)
                if(mapDtoToDao(client).isPresent())
                    list.add(mapDtoToDao(client).get());

        return list;
    }
}
