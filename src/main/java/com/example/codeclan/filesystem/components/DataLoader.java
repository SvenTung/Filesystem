package com.example.codeclan.filesystem.components;

import com.example.codeclan.filesystem.models.File;
import com.example.codeclan.filesystem.models.Folder;
import com.example.codeclan.filesystem.models.User;
import com.example.codeclan.filesystem.repositories.FileRepository;
import com.example.codeclan.filesystem.repositories.FolderRepository;
import com.example.codeclan.filesystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private UserRepository userRepository;

    public void run(ApplicationArguments args) {
        User ben = new User("Ben");

        Folder desktop = new Folder("Desktop", ben);

        File notes1 = new File("notes1", "txt", 300, desktop);
        File notes2 = new File("notes2", "txt", 280, desktop);

        desktop.addFile(notes1);
        desktop.addFile(notes2);

        ben.addFolder(desktop);


        Folder scans = new Folder("Scans", ben);

        File scan1 = new File("Scan 001", "jpg", 560, scans);
        File scan2 = new File("Scan 002", "jpg", 640, scans);

        scans.addFile(scan1);
        scans.addFile(scan2);

        ben.addFolder(scans);


        userRepository.save(ben);

        folderRepository.save(desktop);
        folderRepository.save(scans);

        fileRepository.save(notes1);
        fileRepository.save(notes2);
        fileRepository.save(scan1);
        fileRepository.save(scan2);

    }
}
