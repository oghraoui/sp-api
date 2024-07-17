package com.sbg.spapi.services;

import com.microsoft.graph.models.DriveItem;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class GraphService {
    private final GraphServiceClient graphClient;

    public String uploadFile(String driveId, String filename, InputStream inputStream) {
        DriveItem uploadedFile = graphClient.drives().byDriveId(driveId).items().byDriveItemId("root:/" + filename + ":").content().put(inputStream);
        return String.format("File uploaded: ID[%s] - '%s'", uploadedFile.getId(), uploadedFile.getName());
    }

    public void deleteFile(String driveId, String fileId) {
        graphClient.drives().byDriveId(driveId).items().byDriveItemId(fileId).delete();
    }

}
