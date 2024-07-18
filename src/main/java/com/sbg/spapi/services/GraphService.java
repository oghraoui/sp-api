package com.sbg.spapi.services;

import com.microsoft.graph.models.DriveItem;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.sbg.spapi.dao.DocumentType;
import com.sbg.spapi.dao.dto.SPDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class GraphService {
    private final GraphServiceClient graphClient;
    private final DocumentTypeService documentTypeService;

    public SPDocument uploadFile(String type, String module, String project, String filename, InputStream inputStream) {
        DocumentType documentType = documentTypeService.findDocumentType(type, module, project);

        DriveItem uploadedFile = graphClient.drives().byDriveId(documentType.getDriveId())
                .items()
                .byDriveItemId("root:/" + filename + ":")
                .content()
                .put(inputStream);
        return new SPDocument(uploadedFile.getId(), uploadedFile.getName(), uploadedFile.getWebUrl());
    }

    public void deleteFile(String driveId, String fileId) {
        graphClient.drives().byDriveId(driveId)
                .items()
                .byDriveItemId(fileId)
                .delete();
    }
}
