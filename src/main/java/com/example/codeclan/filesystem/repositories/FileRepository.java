package com.example.codeclan.filesystem.repositories;

import com.example.codeclan.filesystem.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long>{
}
