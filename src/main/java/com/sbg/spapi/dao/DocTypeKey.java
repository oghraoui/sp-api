package com.sbg.spapi.dao;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocTypeKey that = (DocTypeKey) o;
        return Objects.equals(name, that.name) && Objects.equals(moduleCode, that.moduleCode) && Objects.equals(projectCode, that.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, moduleCode, projectCode);
    }
}
