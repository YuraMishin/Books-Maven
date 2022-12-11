package com.mishinyura.booksmaven.utils.files;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Slf4j
public class FileUploadUtil {

    private FileUploadUtil() {
    }

    public static void saveFile(
            String uploadDir,
            String fileName,
            MultipartFile multipartFile) {

        var uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                log.error(
                        "{} - Exception caught - {}",
                        LocalDateTime.now(),
                        "IOException. Cannot create directory" + uploadPath
                );
            }
        }

        try (var is = multipartFile.getInputStream()) {
            var filePath = uploadPath.resolve(fileName);
            Files.copy(is, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error(
                    "{} - Exception caught - {}",
                    LocalDateTime.now(),
                    "IOException! Could not save file:" + fileName
            );
        }
    }

    public static void cleanDir(String dir) {

    }
}
