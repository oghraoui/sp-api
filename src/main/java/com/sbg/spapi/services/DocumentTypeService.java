package com.sbg.spapi.services;

import com.sbg.spapi.dao.DocTypeKey;
import com.sbg.spapi.dao.DocumentType;
import com.sbg.spapi.dao.repositories.DocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

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
        mir.setSpField1Name("_x0066_1");
        mir.setSpField2Name("_x0066_2");
        mir.setSpField3Name("_x0066_3");
        mir.setSpField4Name("_x0066_4");
        mir.setSpField5Name("_x0066_5");
        mir.setSpField6Name("_x0066_6");
        mir.setSpField7Name("_x0066_7");
        mir.setSpField8Name("_x0066_8");
        mir.setSpField9Name("_x0066_9");
        mir.setSpField10Name("_x0066_10");
        repository.save(mir);

        var mir2 = new DocumentType();
        mir2.setId(new DocTypeKey("MIR", "QAQC", "P1151"));
        mir2.setSiteId("11e8315e-dabb-49f3-8a96-8336767cc7c6");
        mir2.setDriveId("b!XjHoEbva80mKloM2dnzHxqj1CY-Y3w5Mp9Lozt_dOcZconxlwxw3R53yA35Bh-FR");
        mir2.setSpField1Name("_x0066_1");
        mir2.setSpField2Name("_x0066_2");
        mir2.setSpField3Name("_x0066_3");
        mir2.setSpField4Name("_x0066_4");
        mir2.setSpField5Name("_x0066_5");
        mir2.setSpField6Name("_x0066_6");
        mir2.setSpField7Name("_x0066_7");
        mir2.setSpField8Name("_x0066_8");
        mir2.setSpField9Name("_x0066_9");
        mir2.setSpField10Name("_x0066_10");
        repository.save(mir2);

        var project = new DocumentType();
        project.setId(new DocTypeKey("WIR", "QAQC", "P1150"));
        project.setSiteId("11e8315e-dabb-49f3-8a96-8336767cc7c6");
        project.setDriveId("b!XjHoEbva80mKloM2dnzHxqj1CY-Y3w5Mp9Lozt_dOcZconxlwxw3R53yA35Bh-FR");
        project.setSpField1Name("_x0066_1");
        project.setSpField2Name("_x0066_2");
        project.setSpField3Name("_x0066_3");
        project.setSpField4Name("_x0066_4");
        project.setSpField5Name("_x0066_5");
        project.setSpField6Name("_x0066_6");
        project.setSpField7Name("_x0066_7");
        project.setSpField8Name("_x0066_8");
        project.setSpField9Name("_x0066_9");
        project.setSpField10Name("_x0066_10");
        repository.save(project);
    }

}
