package com.example.demovaadinuploadblob.model;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import com.example.demovaadinuploadblob.common.JsonDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class FileDmp {
    @Id
    @GeneratedValue
    private Long id;
    private String fileName;
    private String userId;

    @JsonIgnore
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;

    private String createdBy;
    @CreatedDate
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private Date createdDate;
}
