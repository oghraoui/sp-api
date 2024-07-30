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
import com.sbg.spapi.dao.repositories.SPDriveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class GraphService {
    private final GraphServiceClient graphClient;
    private final DocumentTypeService documentTypeService;
    private final SPDocumentRepository spDocumentRepository;
    private final SPDriveRepository spDriveRepository;

    public SPDrive getDriveById(String driveId) {
        return spDriveRepository.findById(driveId).orElseThrow();
    }

    public SPDocument uploadFile(UploadRequest uploadRequest, String filename, InputStream inputStream) {
        DocumentType documentType = documentTypeService.findDocumentType(uploadRequest.documentType(), uploadRequest.module(), uploadRequest.project());
        SPDrive spDrive = getDriveById(documentType.getDriveId());

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

    public void deleteFile(String driveId, String fileId) {
        graphClient.drives().byDriveId(driveId)
                .items()
                .byDriveItemId(fileId)
                .delete();
    }

    @Bean
    public void initDrives() {
        if (spDriveRepository.count() == 0) {
            SPDrive spDrive = new SPDrive();
            spDrive.setDriveId("b!XjHoEbva80mKloM2dnzHxqj1CY-Y3w5Mp9Lozt_dOcZconxlwxw3R53yA35Bh-FR");
            spDrive.setDriveName("BasicTest");
            spDrive.setListId("657ca25c-1cc3-4737-9df2-037e4187e151");
            spDrive.setSiteId("11e8315e-dabb-49f3-8a96-8336767cc7c6");
            spDrive.setSiteName("SaudiBinLadinGroupTest");
            spDriveRepository.save(spDrive);
            log.info("Created initial drive");
        }
    }

    private FieldValueSet toFieldValueSet(DocumentType documentType, UploadRequest uploadRequest) {
        FieldValueSet fieldSet = new FieldValueSet();
        fieldSet.getAdditionalData().put(documentType.getField1(), uploadRequest.field1());
        fieldSet.getAdditionalData().put(documentType.getField2(), uploadRequest.field2());
        fieldSet.getAdditionalData().put(documentType.getField3(), uploadRequest.field3());
        fieldSet.getAdditionalData().put(documentType.getField4(), uploadRequest.field4());
        fieldSet.getAdditionalData().put(documentType.getField5(), uploadRequest.field5());
        fieldSet.getAdditionalData().put(documentType.getField6(), uploadRequest.field6());
        fieldSet.getAdditionalData().put(documentType.getField7(), uploadRequest.field7().format(DateTimeFormatter.ISO_DATE_TIME));
        fieldSet.getAdditionalData().put(documentType.getField8(), uploadRequest.field8().format(DateTimeFormatter.ISO_DATE_TIME));
        fieldSet.getAdditionalData().put(documentType.getField9(), uploadRequest.field9());
        fieldSet.getAdditionalData().put(documentType.getField10(), uploadRequest.field10());
        return fieldSet;
    }
}
