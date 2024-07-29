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
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String field10;
}
