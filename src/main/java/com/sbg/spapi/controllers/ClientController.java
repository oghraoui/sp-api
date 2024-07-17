package com.sbg.spapi.controllers;

import com.sbg.spapi.dao.dto.ClientCredentials;
import com.sbg.spapi.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ClientCredentials> createClient() {
        return ResponseEntity.ok(clientService.create());
    }

    @PatchMapping("/enable")
    public ResponseEntity<?> enableClient(@RequestParam("client-id") String clientId) {
        clientService.changeActiveStatus(clientId, true);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/disable")
    public ResponseEntity<?> disableClient(@RequestParam("client-id") String clientId) {
        clientService.changeActiveStatus(clientId, false);
        return ResponseEntity.ok().build();
    }
}
