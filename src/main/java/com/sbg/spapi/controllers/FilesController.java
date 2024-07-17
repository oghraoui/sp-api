package com.sbg.spapi.controllers;

import com.sbg.spapi.services.GraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FilesController {

    private final GraphService graphService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("type") String type,
                             @RequestParam("module") String module,
                             @RequestParam(value = "project", required = false) String project,
                             @RequestParam("file") MultipartFile file) throws Exception {
        return graphService.uploadFile(type, module, Objects.isNull(project) ? "" : project, file.getOriginalFilename(), file.getInputStream());
    }

    @DeleteMapping("/delete")
    public String deleteFile(@RequestParam("drive") String drive, @RequestParam("file") String file) {
        graphService.deleteFile(drive, file);
        return "Deleted file: " + file;
    }

}
