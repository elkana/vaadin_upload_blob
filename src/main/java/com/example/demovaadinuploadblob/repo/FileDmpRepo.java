package com.example.demovaadinuploadblob.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demovaadinuploadblob.model.FileDmp;

public interface FileDmpRepo extends JpaRepository<FileDmp, Long>{
    FileDmp findFirstByFileName(String fileName);
}
