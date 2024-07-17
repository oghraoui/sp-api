package com.sbg.spapi.services;

import com.sbg.spapi.dao.DocTypeKey;
import com.sbg.spapi.dao.DocumentType;
import com.sbg.spapi.dao.repositories.DocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentTypeService {

    private final DocumentTypeRepository repository;

    public List<DocumentType> getAllDocumentTypes() {
        return repository.findAll();
    }

    public DocumentType findDocumentType(String name, String module, String project) {
        return repository.findById(new DocTypeKey(name, module, project)).orElseThrow();
    }

    public DocumentType createDocumentType(DocumentType documentType) throws Exception {
        if (repository.findById(documentType.getId()).isEmpty()) {
            return repository.save(documentType);
        } else {
            throw new Exception("Document Type exists");
        }
    }

    @Bean
    public void initialize() {
        var mir = new DocumentType();
        mir.setId(new DocTypeKey("MIR", "QAQC", "P1150"));
        mir.setSiteId("11e8315e-dabb-49f3-8a96-8336767cc7c6");
        mir.setDriveId("b!XjHoEbva80mKloM2dnzHxqj1CY-Y3w5Mp9Lozt_dOcZconxlwxw3R53yA35Bh-FR");
        repository.save(mir);

        var mir2 = new DocumentType();
        mir2.setId(new DocTypeKey("MIR", "QAQC", "P1151"));
        mir2.setSiteId("11e8315e-dabb-49f3-8a96-8336767cc7c6");
        mir2.setDriveId("b!XjHoEbva80mKloM2dnzHxqj1CY-Y3w5Mp9Lozt_dOcYlZrRreQrXSJw_Nl5bs2GF");
        repository.save(mir2);

        var project = new DocumentType();
        project.setId(new DocTypeKey("WIR", "QAQC", "P1150"));
        project.setSiteId("11e8315e-dabb-49f3-8a96-8336767cc7c6");
        project.setDriveId("b!XjHoEbva80mKloM2dnzHxqj1CY-Y3w5Mp9Lozt_dOcZconxlwxw3R53yA35Bh-FR");
        repository.save(project);
    }

}
