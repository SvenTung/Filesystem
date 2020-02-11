package com.example.codeclan.filesystem.controllers;

import com.example.codeclan.filesystem.models.Folder;
import com.example.codeclan.filesystem.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class FolderController {
    @Autowired
    private FolderRepository folderRepository;

    @GetMapping(value = "/folders")
    public ResponseEntity<List<Folder>> getAllFolders(){
        return new ResponseEntity<>(folderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/folders/{id}")
    public ResponseEntity getFolderById(@PathVariable Long id){
        return new ResponseEntity<>(folderRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/folders")
    public ResponseEntity<Folder> createFolder(@RequestBody Folder folder){
        folderRepository.save(folder);
        return new ResponseEntity<>(folder, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/folders")
    public void deleteAllFolders(){
        folderRepository.deleteAll();
    }

    @DeleteMapping(value = "/folders/{id}")
    public Optional<Folder> deleteFolderById(@PathVariable Long id){
        Optional<Folder> folder = folderRepository.findById(id);
        folderRepository.deleteById(id);
        return folder;
    }

    @PatchMapping(value = "/folders/{id}")
    public ResponseEntity<Folder> updateFolder(@RequestBody Folder payload, @PathVariable Long id){
        if (payload.getId() == null){
            payload.setId(id);
        } else if(payload.getId() != id){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        folderRepository.save(payload);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
}
