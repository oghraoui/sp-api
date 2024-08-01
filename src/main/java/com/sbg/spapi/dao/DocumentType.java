package com.sbg.spapi.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document-types")
@Data
@NoArgsConstructor
public class DocumentType {

    /**
     * Field value correspond to sharepoint column names
     */

    @EmbeddedId
    private DocTypeKey id;
    private String siteId;
    private String driveId;
    private String spField1Name;
    private String spField2Name;
    private String spField3Name;
    private String spField4Name;
    private String spField5Name;
    private String spField6Name;
    private String spField7Name;
    private String spField8Name;
    private String spField9Name;
    private String spField10Name;
}
