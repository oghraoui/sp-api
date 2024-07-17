package com.sbg.spapi.controllers;

import com.sbg.spapi.services.GraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FilesController {

    private final GraphService graphService;

    @PostMapping("/upload")
    public String uploadFile(@RequestPart("drive") String drive, @RequestParam("file") MultipartFile file) throws IOException {
        return graphService.uploadFile(drive, file.getOriginalFilename(), file.getInputStream());
    }

    @DeleteMapping("/delete")
    public String deleteFile(@RequestParam("drive") String drive, @RequestParam("file") String file) {
        graphService.deleteFile(drive, file);
        return "Deleted file: " + file;
    }

}
