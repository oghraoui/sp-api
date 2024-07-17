package com.sbg.spapi.services;

import com.sbg.spapi.dao.Client;
import com.sbg.spapi.dao.ClientCredentials;
import com.sbg.spapi.dao.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("client-service")
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientRepository clientRepository;

    @Value("${init.client-id}") String clientId;
    @Value("${init.client-secret}") String clientSecret;

    public ClientCredentials create() {
        Client client = new Client();
        client.setClientId(UUID.randomUUID().toString());
        client.setClientSecret(UUID.randomUUID().toString());
        client.setActive(true);
        client = repository.save(client);
        return new ClientCredentials(client.getClientId(), client.getClientSecret());
    }

    public void changeActiveStatus(String clientId, Boolean active) {
        Client client = clientRepository.findByClientId(clientId).orElseThrow();
        client.setActive(active);
        repository.save(client);
    }

    public boolean verifyClientCredentials(String clientId, String clientSecret) {
        Optional<Client> client = clientRepository.findByClientId(clientId);
        return client.isPresent() && client.get().getActive() && client.get().getClientSecret().equals(clientSecret);
    }

    @Bean
    public void createInitialClient() {
        if (repository.count() == 0) {
            Client client = new Client();
            client.setClientId(clientId);
            client.setClientSecret(clientSecret);
            client.setActive(true);
            repository.save(client);
            log.info("Created new client {}", clientId);
        }
    }
}
