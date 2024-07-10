package com.MobileDev.service;

import com.MobileDev.model.Client;
import com.MobileDev.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Map<String, Object>> getAllClients(String nom, String ville, String sort, String fields, Integer limit) {
        List<Client> clients = clientRepository.findAll();

        if (nom != null) {
            clients = clients.stream().filter(client -> client.getNom().contains(nom)).collect(Collectors.toList());
        }

        if (ville != null) {
            clients = clients.stream().filter(client -> client.getVille().contains(ville)).collect(Collectors.toList());
        }

        if (sort != null) {
            switch (sort) {
                case "-nom":
                    clients = clients.stream().sorted((c1, c2) -> c2.getNom().compareTo(c1.getNom())).collect(Collectors.toList());
                    break;
                case "nom":
                    clients = clients.stream().sorted((c1, c2) -> c1.getNom().compareTo(c2.getNom())).collect(Collectors.toList());
                    break;
                case "-date_creation":
                    clients = clients.stream().sorted((c1, c2) -> c2.getDateCreation().compareTo(c1.getDateCreation())).collect(Collectors.toList());
                    break;
                case "date_creation":
                    clients = clients.stream().sorted((c1, c2) -> c1.getDateCreation().compareTo(c2.getDateCreation())).collect(Collectors.toList());
                    break;
                case "-id":
                    clients = clients.stream().sorted((c1, c2) -> c2.getId().compareTo(c1.getId())).collect(Collectors.toList());
                    break;
                case "id":
                    clients = clients.stream().sorted((c1, c2) -> c1.getId().compareTo(c2.getId())).collect(Collectors.toList());
                    break;
                case "-ville":
                    clients = clients.stream().sorted((c1, c2) -> c2.getVille().compareTo(c1.getVille())).collect(Collectors.toList());
                    break;
                case "ville":
                    clients = clients.stream().sorted((c1, c2) -> c1.getVille().compareTo(c2.getVille())).collect(Collectors.toList());
                    break;
                default:
                    break;
            }
        }

        if (limit != null && limit > 0) {
            clients = clients.stream().limit(limit).collect(Collectors.toList());
        }

        Set<String> fieldSet = (fields != null) ? Stream.of(fields.split(",")).collect(Collectors.toSet()) : null;

        return clients.stream().map(client -> filterFields(client, fieldSet)).collect(Collectors.toList());
    }

    private Map<String, Object> filterFields(Client client, Set<String> fields) {
        Map<String, Object> filteredClient = new HashMap<>();
        filteredClient.put("id", client.getId());
        if (fields == null || fields.contains("nom")) filteredClient.put("nom", client.getNom());
        if (fields == null || fields.contains("email")) filteredClient.put("email", client.getEmail());
        if (fields == null || fields.contains("tel")) filteredClient.put("tel", client.getTel());
        if (fields == null || fields.contains("adresse")) filteredClient.put("adresse", client.getAdresse());
        if (fields == null || fields.contains("codePostal")) filteredClient.put("codePostal", client.getCodePostal());
        if (fields == null || fields.contains("ville")) filteredClient.put("ville", client.getVille());
        if (fields == null || fields.contains("dateCreation")) filteredClient.put("dateCreation", client.getDateCreation());
        return filteredClient;
    }

    public Client getClientById(String id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client saveClient(Client client) {
        if (client.getId() == null || client.getId().isEmpty()) {
            client.setId(UUID.randomUUID().toString());
        }
        return clientRepository.save(client);
    }

    public Client updateClient(String id, Map<String, Object> updates) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            return null;
        }
        updates.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    client.setNom((String) value);
                    break;
                case "email":
                    client.setEmail((String) value);
                    break;
                case "tel":
                    client.setTel((String) value);
                    break;
                case "adresse":
                    client.setAdresse((String) value);
                    break;
                case "codePostal":
                    client.setCodePostal((String) value);
                    break;
                case "ville":
                    client.setVille((String) value);
                    break;
            }
        });
        return clientRepository.save(client);
    }
}
