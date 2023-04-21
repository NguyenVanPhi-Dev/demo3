package com.example.demo3.orther;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadImage {
 public static void uploadImage(String filePath, String fileName, MultipartFile multipartFile) throws IOException {
     Path path = Paths.get(filePath);
     if(!Files.exists(path)){
         Files.createDirectories(path);
     }
     try (InputStream inputStream = multipartFile.getInputStream()){
            Path upload = path.resolve(fileName);
//            System.out.println("handel upload image"+fileName);
            Files.copy(inputStream,upload, StandardCopyOption.REPLACE_EXISTING);
     }catch (IOException ioe) {
         throw new IOException("Could not save image file: " + fileName, ioe);
     }
 }
}
