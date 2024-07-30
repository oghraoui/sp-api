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
        mir.setField1("_x0066_1");
        mir.setField2("_x0066_2");
        mir.setField3("_x0066_3");
        mir.setField4("_x0066_4");
        mir.setField5("_x0066_5");
        mir.setField6("_x0066_6");
        mir.setField7("_x0066_7");
        mir.setField8("_x0066_8");
        mir.setField9("_x0066_9");
        mir.setField10("_x0066_10");
        repository.save(mir);

        var mir2 = new DocumentType();
        mir2.setId(new DocTypeKey("MIR", "QAQC", "P1151"));
        mir2.setSiteId("11e8315e-dabb-49f3-8a96-8336767cc7c6");
        mir2.setDriveId("b!XjHoEbva80mKloM2dnzHxqj1CY-Y3w5Mp9Lozt_dOcZconxlwxw3R53yA35Bh-FR");
        mir2.setField1("f1");
        mir2.setField2("f2");
        mir2.setField3("f3");
        mir2.setField4("f4");
        mir2.setField5("f5");
        mir2.setField6("f6");
        mir2.setField7("f7");
        mir2.setField8("f8");
        mir2.setField9("f9");
        mir2.setField10("f10");
        repository.save(mir2);

        var project = new DocumentType();
        project.setId(new DocTypeKey("WIR", "QAQC", "P1150"));
        project.setSiteId("11e8315e-dabb-49f3-8a96-8336767cc7c6");
        project.setDriveId("b!XjHoEbva80mKloM2dnzHxqj1CY-Y3w5Mp9Lozt_dOcZconxlwxw3R53yA35Bh-FR");
        project.setField1("f1");
        project.setField2("f2");
        project.setField3("");
        project.setField4("f4");
        project.setField5("");
        project.setField6("");
        project.setField7("");
        project.setField8("");
        project.setField9("");
        project.setField10("");
        repository.save(project);
    }

}
