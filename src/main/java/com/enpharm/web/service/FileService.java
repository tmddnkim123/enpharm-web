package com.enpharm.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

@Service
public class FileService {
    @Autowired
    ServletContext context;
    @Value("${upload.path}")
    private String uploadDir;

    private static String getRandomString(int length)
    {
        String result = "";
        Random random = new Random();

        String table = "abcdefghijklmnopqrstuvwxyz";

        for (int i=0 ; i<length ; i++)
        {
            result += table.charAt(random.nextInt(table.length()));
        }
        return result;
    }

    public boolean initUploadPath() {
        File file = new File(context.getRealPath(uploadDir));
        if(!file.exists()) {
            Path uploadPath = Paths.get(context.getRealPath(uploadDir));

            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public String filter(String fileName){
        String[] blackList = {".jsp", ".php", ".asp", ".exe"};
        String filteredFileName = fileName;
        for(String ext: blackList) {
            filteredFileName = filteredFileName.replace(ext, "");
        }
        return filteredFileName;
    }

    public String fileUpload(MultipartFile file, String originalFileName) {
        if(!initUploadPath())
            return null;

        String filePathString = uploadDir + "/" + getRandomString(8) + "_" + originalFileName;

        Path realFilePath = Paths.get(context.getRealPath("/") + "/" + filePathString);

        try {
            Files.copy(file.getInputStream(), realFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePathString;
    }
}