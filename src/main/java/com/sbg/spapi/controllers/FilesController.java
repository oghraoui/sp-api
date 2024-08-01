package com.sbg.spapi.controllers;

import com.sbg.spapi.dao.SPDocument;
import com.sbg.spapi.dao.dto.UploadRequest;
import com.sbg.spapi.services.GraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FilesController {

    private final GraphService graphService;

    @PostMapping("/upload")
    public ResponseEntity<SPDocument> uploadFile(
            @RequestParam("documentType") String documentType,
            @RequestParam("module") String module,
            @RequestParam("project") String project,
            @RequestParam("pkId") Long pkId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "field1", required = false) String field1,
            @RequestParam(value = "field2", required = false) String field2,
            @RequestParam(value = "field3", required = false) String field3,
            @RequestParam(value = "field4", required = false) String field4,
            @RequestParam(value = "field5", required = false) String field5,
            @RequestParam(value = "field6", required = false) String field6,
            @RequestParam(value = "field7", required = false) LocalDateTime field7,
            @RequestParam(value = "field8", required = false) LocalDateTime field8,
            @RequestParam(value = "field9", required = false) Long field9,
            @RequestParam(value = "field10", required = false) Long field10
    ) throws Exception {
        UploadRequest request = new UploadRequest(documentType, module, project, pkId, field1, field2, field3, field4, field5, field6, field7, field8, field9, field10);
        return ResponseEntity.ok(graphService.uploadFile(request, file.getOriginalFilename(), file.getInputStream()));
    }

    @DeleteMapping("/delete")
    public String deleteFile(@RequestParam("drive") String drive, @RequestParam("file") String file) {
        graphService.deleteFile(drive, file);
        return "Deleted file: " + file;
    }

    @GetMapping("/get")
    public ResponseEntity<SPDocument> find(@RequestParam("systemId") Long systemId) {
        return ResponseEntity.ok(graphService.getDocument(systemId));
    }

    @GetMapping("/get/content")
    public ResponseEntity<InputStreamResource> content(@RequestParam("systemId") Long systemId) {
        SPDocument doc = graphService.getDocument(systemId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + doc.getName() + "\"")
                .body(graphService.getDocumentContent(systemId));
    }

}
