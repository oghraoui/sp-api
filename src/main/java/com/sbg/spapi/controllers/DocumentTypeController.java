package com.sbg.spapi.controllers;

import com.sbg.spapi.dao.DocumentType;
import com.sbg.spapi.services.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/doctypes")
@RequiredArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(documentTypeService.getAllDocumentTypes());
    }

    @GetMapping("/get")
    public ResponseEntity<?> find(@RequestParam("module") String module, @RequestParam(value = "project", required = false) String project, @RequestParam("docType") String docType) {
        return ResponseEntity.ok(documentTypeService.findDocumentType(docType, module, Objects.isNull(project) ? "" : project));
    }

    @PostMapping("/create")
    public ResponseEntity<?> addDocumentType(@RequestBody DocumentType documentType) throws Exception {
        return ResponseEntity.ok(documentTypeService.createDocumentType(documentType));
    }

}
