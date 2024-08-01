package com.sbg.spapi.controllers;

import com.microsoft.graph.models.Drive;
import com.sbg.spapi.dao.SPDrive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/drives")
@RequiredArgsConstructor
public class DrivesController {

    @GetMapping("/list")
    public ResponseEntity<?> getDrives() {
        return ResponseEntity.badRequest().body("To implement");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getDrive() {
        return ResponseEntity.badRequest().body("To implement");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDrive(@RequestBody SPDrive drive) {
        return ResponseEntity.badRequest().body("To implement");
    }

}
