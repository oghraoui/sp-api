package com.sbg.spapi.controllers;

import com.sbg.spapi.dao.dto.ClientCredentials;
import com.sbg.spapi.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ClientCredentials> createClient() {
        return ResponseEntity.ok(clientService.create());
    }

    @PatchMapping("/activate")
    public ResponseEntity<?> enableClient(@RequestParam("clientId") String clientId) {
        clientService.changeActiveStatus(clientId, true);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/deactivate")
    public ResponseEntity<?> disableClient(@RequestParam("clientId") String clientId) {
        clientService.changeActiveStatus(clientId, false);
        return ResponseEntity.ok().build();
    }
}
