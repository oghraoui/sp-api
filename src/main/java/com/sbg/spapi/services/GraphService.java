package com.sbg.spapi.services;

import com.microsoft.graph.models.DriveItem;
import com.microsoft.graph.models.FieldValueSet;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.nimbusds.jose.shaded.gson.JsonPrimitive;
import com.sbg.spapi.dao.DocumentType;
import com.sbg.spapi.dao.SPDocument;
import com.sbg.spapi.dao.dto.UploadRequest;
import com.sbg.spapi.dao.repositories.SPDocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class GraphService {
    private final GraphServiceClient graphClient;
    private final DocumentTypeService documentTypeService;
    private final SPDocumentRepository spDocumentRepository;

    public SPDocument uploadFile(UploadRequest uploadRequest, String filename, InputStream inputStream) {
        DocumentType documentType = documentTypeService.findDocumentType(uploadRequest.documentType(), uploadRequest.module(), uploadRequest.project());

        Map<String, JsonPrimitive> metadata = new HashMap<>();
        metadata.put(documentType.getField1(), new JsonPrimitive(uploadRequest.field1()));
        metadata.put(documentType.getField2(), new JsonPrimitive(uploadRequest.field2()));

        DriveItem uploadedFile = graphClient.drives().byDriveId(documentType.getDriveId())
                .items()
                .byDriveItemId("root:/" + filename + ":")
                .content()
                .put(inputStream);

        graphClient.drives().byDriveId(documentType.getDriveId()).items().byDriveItemId(uploadedFile.getId()).listItem().get().getAdditionalData().putAll(metadata);

        SPDocument uploadedDocument = new SPDocument();
        uploadedDocument.setPkId(uploadRequest.pkId());
        uploadedDocument.setSpId(uploadedFile.getId());
        uploadedDocument.setName(uploadedFile.getName());
        uploadedDocument.setUrl(uploadedFile.getWebUrl());
        uploadedDocument.setField1(uploadRequest.field1());
        uploadedDocument.setField2(uploadRequest.field2());
        uploadedDocument.setField3(uploadRequest.field3());
        uploadedDocument.setField4(uploadRequest.field4());
        uploadedDocument.setField5(uploadRequest.field5());
        uploadedDocument.setField6(uploadRequest.field6());
        uploadedDocument.setField7(uploadRequest.field7());
        uploadedDocument.setField8(uploadRequest.field8());
        uploadedDocument.setField9(uploadRequest.field9());
        uploadedDocument.setField10(uploadRequest.field10());
        uploadedDocument.setDocumentType(documentType.getId().getName());
        uploadedDocument.setProjectCode(documentType.getId().getProjectCode());
        uploadedDocument.setModuleCode(documentType.getId().getModuleCode());
        return spDocumentRepository.save(uploadedDocument);
    }

    public void addMetadata(SPDocument spDocument) {
        DocumentType documentType = documentTypeService.findDocumentType(spDocument.getDocumentType(), spDocument.getModuleCode(), spDocument.getProjectCode());
        //TODO: Figure out how to upload metadata to file

    }

    public void deleteFile(String driveId, String fileId) {
        graphClient.drives().byDriveId(driveId)
                .items()
                .byDriveItemId(fileId)
                .delete();
    }
}
