package com.sbg.spapi.controllers;

import com.sbg.spapi.dao.SPDrive;
import com.sbg.spapi.services.DriveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drives")
@RequiredArgsConstructor
public class DrivesController {

    private final DriveService driveService;

    @GetMapping("/list")
    public ResponseEntity<List<SPDrive>> getDrives() {
        return ResponseEntity.ok(driveService.getAllDrives());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getDrive(@RequestParam String driveId) {
        return ResponseEntity.ok(driveService.getDriveById(driveId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDrive(@RequestBody SPDrive drive) {
        return ResponseEntity.ok(driveService.createDrive(drive));
    }

}
