package com.sbg.spapi.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document-types")
@Data
@NoArgsConstructor
public class DocumentType {
    @EmbeddedId
    private DocTypeKey id;
    private String siteId;
    private String driveId;
}
