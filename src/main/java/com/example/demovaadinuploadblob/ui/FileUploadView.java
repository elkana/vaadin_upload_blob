package com.example.demovaadinuploadblob.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import com.example.demovaadinuploadblob.model.FileDmp;
import com.example.demovaadinuploadblob.repo.FileDmpRepo;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.upload.MultiFileReceiver;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.Route;

@Route
public class FileUploadView extends Div {

    public FileUploadView(FileDmpRepo repo) {

        File folder = new File("elkana-uploaded-files");
        // MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        var receiver = setupUploadDir(folder);

        Upload upload = new Upload(receiver);
        upload.setAutoUpload(false);
        // int maxFileSizeInBytes = 10 * 1024 * 1024; // 10MB
        // upload.setMaxFileSize(maxFileSizeInBytes);
        // upload.setDropLabel(new Text("Drop file here (max 1MB)"));
        // upload.setAcceptedFileTypes("application/pdf", ".pdf");
        // upload.setDropAllowed(false);

        upload.addSucceededListener(e -> {
            String fileName = e.getFileName();
            // reload file       
            try (InputStream is = new FileInputStream(new File(folder.getPath() + "/" + fileName))) {
                byte[] bytes = IOUtils.toByteArray(is);
                repo.save(FileDmp.builder().fileName(fileName).userId("Unknown").image(bytes)
                        .build());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        add(upload);
    }

    // uploaded location will be in C:\git\learn\demo-vaadin-upload-blob\elkana-uploaded-files
    private Receiver setupUploadDir(File folder) {
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return (MultiFileReceiver) (filename, mimeType) -> {
            File file = new File(folder, filename);
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                return null;
            }
        };
    }

}
