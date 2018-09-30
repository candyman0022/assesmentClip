package com.backend.model;


import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

class Utils {

    File[] getFiles(String userId) {
        File dir = new File(".");
        FileFilter fileFilter = new WildcardFileFilter(userId + "_*");
        return dir.listFiles(fileFilter);
    }

    String getFilePath(String fileName) {
        return FileSystems.getDefault().getPath(fileName).toAbsolutePath().toString();
    }


    JSONObject getJsonObject(File file) throws IOException {
        String writtenFile = new String(Files.readAllBytes(Paths.get(String.valueOf(file))));
        return new JSONObject(writtenFile);
    }
}
