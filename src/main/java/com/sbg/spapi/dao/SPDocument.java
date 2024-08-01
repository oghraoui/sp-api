package com.sbg.spapi.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "documents")
@NoArgsConstructor
public class SPDocument {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private Long pkId;
    private String spId;
    private String name;
    private String url;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private LocalDateTime field7;
    private LocalDateTime field8;
    private Long field9;
    private Long field10;
    private String documentType;
    private String projectCode;
    private String moduleCode;
}
