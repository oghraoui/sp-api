package com.sbg.spapi.services;

import com.sbg.spapi.dao.SPDrive;
import com.sbg.spapi.dao.repositories.SPDriveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriveService {

    private final SPDriveRepository spDriveRepository;

    public SPDrive getDriveById(String driveId) {
        return spDriveRepository.findById(driveId).orElseThrow();
    }

    public List<SPDrive> getAllDrives() {
        return spDriveRepository.findAll();
    }

    public SPDrive createDrive(SPDrive drive) {
        Optional<SPDrive> existingDrive = spDriveRepository.findById(drive.getDriveId());
        return existingDrive.orElseGet(() -> spDriveRepository.save(drive));
    }

    @Bean
    public void initDrives() {
        if (spDriveRepository.count() == 0) {
            SPDrive spDrive = new SPDrive();
            spDrive.setDriveId("b!XjHoEbva80mKloM2dnzHxqj1CY-Y3w5Mp9Lozt_dOcZconxlwxw3R53yA35Bh-FR");
            spDrive.setDriveName("BasicTest");
            spDrive.setListId("657ca25c-1cc3-4737-9df2-037e4187e151");
            spDrive.setSiteId("11e8315e-dabb-49f3-8a96-8336767cc7c6");
            spDrive.setSiteName("SaudiBinLadinGroupTest");
            spDriveRepository.save(spDrive);
            log.info("Created initial drive");
        }
    }
}
