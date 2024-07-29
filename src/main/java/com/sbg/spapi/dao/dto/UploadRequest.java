package com.sbg.spapi.dao.dto;

import java.time.LocalDateTime;

public record UploadRequest(String documentType,
                            String module,
                            String project,
                            Long pkId,
                            String field1,
                            String field2,
                            String field3,
                            String field4,
                            String field5,
                            String field6,
                            LocalDateTime field7,
                            LocalDateTime field8,
                            Long field9,
                            Long field10) { }
