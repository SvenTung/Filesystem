package com.example.codeclan.filesystem.models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "users")
public class User {

    @Column (name = "name")
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Folder> folders;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;


    public User(String name) {
        this.name = name;
        this.folders = new ArrayList<>();
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public void addFolder(Folder folder) {
        this.folders.add(folder);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
