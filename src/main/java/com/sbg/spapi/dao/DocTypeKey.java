package com.sbg.spapi.dao;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor
public class DocTypeKey implements Serializable {
    private String name;
    private String moduleCode;
    private String projectCode;

    public DocTypeKey(String name, String moduleCode, String projectCode) {
        this.name = name;
        this.moduleCode = moduleCode;
        this.projectCode = projectCode;
    }
}
