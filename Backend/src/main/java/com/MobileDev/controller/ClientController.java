package com.MobileDev.controller;

import com.MobileDev.model.Client;
import com.MobileDev.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllClients(
            @RequestHeader("Accept") String acceptHeader,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String ville,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String fields,
            @RequestParam(required = false) Integer limit
    ) {
        if (!acceptHeader.equals("application/api.rest-v1+json")) {
            return ResponseEntity.status(400).body(generateResponse(400, "Versionning erroné", null));
        }

        List<Map<String, Object>> clients = clientService.getAllClients(nom, ville, sort, fields, limit);
        return ResponseEntity.ok(generateResponse(200, "", clients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getClientById(@RequestHeader("Accept") String acceptHeader, @PathVariable String id) {
        if (!acceptHeader.equals("application/api.rest-v1+json")) {
            return ResponseEntity.status(400).body(generateResponse(400, "Versionning erroné", null));
        }

        Client client = clientService.getClientById(id);
        if (client == null) {
            return ResponseEntity.status(404).body(generateResponse(404, "Client not found", null));
        }
        return ResponseEntity.ok(generateResponse(200, "", client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateClient(
            @RequestHeader("Accept") String acceptHeader,
            @RequestHeader("Content-Type") String contentType,
            @PathVariable String id,
            @RequestBody Map<String, Object> updates
    ) {
        if (!acceptHeader.equals("application/api.rest-v1+json")) {
            return ResponseEntity.status(400).body(generateResponse(400, "Versionning erroné", null));
        }
        if (!contentType.equals("application/json")) {
            return ResponseEntity.status(415).body(generateResponse(415, "Invalid Content-Type", null));
        }

        Client updatedClient = clientService.updateClient(id, updates);
        if (updatedClient == null) {
            return ResponseEntity.status(404).body(generateResponse(404, "Client not found", null));
        }
        return ResponseEntity.ok(generateResponse(200, "", updatedClient));
    }

    private Map<String, Object> generateResponse(int code, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("message", message);
        response.put("datas", data);
        response.put("warnings", new String[]{});
        return response;
    }
}
