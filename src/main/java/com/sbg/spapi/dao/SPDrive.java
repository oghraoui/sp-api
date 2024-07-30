package com.sbg.spapi.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "sp_drives")
public class SPDrive {
    @Id
    private String driveId;
    private String driveName;
    private String listId;
    private String siteId;
    private String siteName;
}
