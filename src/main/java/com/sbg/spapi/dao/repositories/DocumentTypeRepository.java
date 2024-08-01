package com.sbg.spapi.dao.repositories;

import com.sbg.spapi.dao.DocTypeKey;
import com.sbg.spapi.dao.DocumentType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, DocTypeKey> {
    @NotNull
    Optional<DocumentType> findById(@NotNull DocTypeKey id);
}
