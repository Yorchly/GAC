package com.example.gac.unit;

import com.example.gac.model.Client;
import com.example.gac.model.dto.ClientDto;
import com.example.gac.model.repository.ClientRepository;
import com.example.gac.service.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks ClientServiceImpl service;

    @Mock ClientRepository repository;

    @Test
    public void testIfServiceUpdateClient()
    {
        // given
        final Client client = Mockito.mock(Client.class);
        client.setId(1);
        client.setDni("12345678A");
        client.setName("Pepe");

        final Client clientUpdated = Mockito.mock(Client.class);
        client.setId(1);
        client.setDni("11111111A");
        client.setName("Jose");

        // when
        Mockito.when(repository.findById(client.getId())).thenReturn(Optional.of(client));
        Mockito.when(repository.save(client)).thenReturn(clientUpdated);

        ResponseEntity<ClientDto> response = service.update(client);

        // then
        Assert.assertEquals(response, ResponseEntity.ok().build());
    }

}
