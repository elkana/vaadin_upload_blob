# Demo Upload using Vaadin and Blob in H2 database

Steps:

1. create column byte[] as @Lob
2. use component Upload, with configured Path to store files.


```java
public class FileDmp {
...
    @JsonIgnore
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;
}
```


## References:

https://vaadin.com/docs/latest/components/upload

https://stackoverflow.com/questions/60564945/how-to-increase-file-size-upload-limit-in-spring-boot-using-embedded-tomcat

