package com.example.demovaadinuploadblob.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demovaadinuploadblob.repo.FileDmpRepo;
import jakarta.servlet.http.HttpServletRequest;


@RestController
public class ControllerFile {

    @Autowired
    FileDmpRepo repo;

    // to download file
    // http://localhost:8080/image?fileName=api_vehicles.jpg
    @GetMapping("/image")
    public ResponseEntity<Resource> getImage(@RequestParam String fileName,
            HttpServletRequest request) {

        var row = repo.findFirstByFileName(fileName);

        var resource = new ByteArrayResource(row.getImage());
        // supaya bisa render di browser, jgn return octet-stream
        // jika pakai octet-stream maka browser akan download daripada render
        String contentType = request.getServletContext().getMimeType(row.getFileName());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                // return
                // ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
                // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;
                // filename=\"" +
                // resource.getFilename() + "\"")
                .body(resource);
    }

}
