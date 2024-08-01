package com.sbg.spapi.services;

import com.microsoft.graph.models.DriveItem;
import com.microsoft.graph.models.FieldValueSet;
import com.microsoft.graph.models.ListItem;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.sbg.spapi.dao.DocumentType;
import com.sbg.spapi.dao.SPDocument;
import com.sbg.spapi.dao.SPDrive;
import com.sbg.spapi.dao.dto.UploadRequest;
import com.sbg.spapi.dao.repositories.SPDocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class GraphService {
    private final GraphServiceClient graphClient;
    private final DocumentTypeService documentTypeService;
    private final DriveService driveService;
    private final SPDocumentRepository spDocumentRepository;

    public SPDocument uploadFile(UploadRequest uploadRequest, String filename, InputStream inputStream) {
        DocumentType documentType = documentTypeService.findDocumentType(uploadRequest.documentType(), uploadRequest.module(), uploadRequest.project());
        SPDrive spDrive = driveService.getDriveById(documentType.getDriveId());

        DriveItem uploadedFile = graphClient.drives().byDriveId(documentType.getDriveId())
                .items()
                .byDriveItemId("root:/" + filename + ":")
                .content()
                .put(inputStream);

        ListItem listItem = graphClient
                .drives().byDriveId(documentType.getDriveId())
                .items().byDriveItemId(uploadedFile.getId())
                .listItem().get();

        listItem.setFields(toFieldValueSet(documentType, uploadRequest));

        graphClient
                .sites().bySiteId(spDrive.getSiteId())
                .lists().byListId(spDrive.getListId())
                .items().byListItemId(listItem.getId())
                .patch(listItem);

        SPDocument uploadedDocument = convertToSPDocument(uploadRequest, uploadedFile, documentType);

        return spDocumentRepository.save(uploadedDocument);
    }

    private static @NotNull SPDocument convertToSPDocument(UploadRequest uploadRequest,
                                                           DriveItem uploadedFile,
                                                           DocumentType documentType) {
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
        return uploadedDocument;
    }

    public void deleteFile(String driveId, String fileId) {
        graphClient.drives().byDriveId(driveId)
                .items()
                .byDriveItemId(fileId)
                .delete();
    }

    public SPDocument getDocument(Long systemId) {
        return spDocumentRepository.findById(systemId).orElseThrow();
    }

    public InputStreamResource getDocumentContent(Long systemId) {
        SPDocument document = spDocumentRepository.findById(systemId).orElseThrow();
        DocumentType documentType = documentTypeService.findDocumentType(document.getDocumentType(), document.getModuleCode(), document.getProjectCode());
        return new InputStreamResource(graphClient
                .drives().byDriveId(documentType.getDriveId())
                .items().byDriveItemId(document.getSpId())
                .content().get());
    }

    private FieldValueSet toFieldValueSet(DocumentType documentType, UploadRequest uploadRequest) {
        FieldValueSet fieldSet = new FieldValueSet();
        if (uploadRequest.field1() != null && !uploadRequest.field1().isEmpty()) {
            fieldSet.getAdditionalData().put(documentType.getSpField1Name(), uploadRequest.field1());
        }
        if (uploadRequest.field2() != null && !uploadRequest.field2().isEmpty()) {
            fieldSet.getAdditionalData().put(documentType.getSpField2Name(), uploadRequest.field2());
        }
        if (uploadRequest.field3() != null && !uploadRequest.field3().isEmpty()) {
            fieldSet.getAdditionalData().put(documentType.getSpField3Name(), uploadRequest.field3());
        }
        if (uploadRequest.field4() != null && !uploadRequest.field4().isEmpty()) {
            fieldSet.getAdditionalData().put(documentType.getSpField4Name(), uploadRequest.field4());
        }
        if (uploadRequest.field5() != null && !uploadRequest.field5().isEmpty()) {
            fieldSet.getAdditionalData().put(documentType.getSpField5Name(), uploadRequest.field5());
        }
        if (uploadRequest.field6() != null && !uploadRequest.field6().isEmpty()) {
            fieldSet.getAdditionalData().put(documentType.getSpField6Name(), uploadRequest.field6());
        }
        if (uploadRequest.field7() != null) {
            fieldSet.getAdditionalData().put(documentType.getSpField7Name(), uploadRequest.field7().format(DateTimeFormatter.ISO_DATE_TIME));
        }
        if (uploadRequest.field8() != null) {
            fieldSet.getAdditionalData().put(documentType.getSpField8Name(), uploadRequest.field8().format(DateTimeFormatter.ISO_DATE_TIME));
        }
        if (uploadRequest.field9() != null) {
            fieldSet.getAdditionalData().put(documentType.getSpField9Name(), uploadRequest.field9());
        }
        if (uploadRequest.field10() != null) {
            fieldSet.getAdditionalData().put(documentType.getSpField10Name(), uploadRequest.field10());
        }
        return fieldSet;
    }
}
