package com.example.codeclan.filesystem.controllers;

import com.example.codeclan.filesystem.models.File;
import com.example.codeclan.filesystem.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FileController {
    @Autowired
    private FileRepository fileRepository;

    @GetMapping(value = "/files")
    public ResponseEntity<List<File>> getAllFiles(){
        return new ResponseEntity<>(fileRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/files/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        return new ResponseEntity<>(fileRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/files")
    public ResponseEntity<File> createFile(@RequestBody File file){
        fileRepository.save(file);
        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/files")
    public void deleteAllFiles(){
        fileRepository.deleteAll();
    }

    @DeleteMapping(value = "/files/{id}")
    public Optional<File> deleteFileById(@PathVariable Long id){
        Optional<File> file = fileRepository.findById(id);
        fileRepository.deleteById(id);
        return file;
    }

    @PatchMapping(value = "/files/{id}")
    public ResponseEntity<File> updateFile(@RequestBody File payload, @PathVariable Long id){
        if (payload.getId() == null){
            payload.setId(id);
        } else if(payload.getId() != id){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        fileRepository.save(payload);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
}
