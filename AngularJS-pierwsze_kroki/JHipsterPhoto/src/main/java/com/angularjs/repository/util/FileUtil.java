package com.angularjs.repository.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 25.08.15.
 */
@Component
public class FileUtil {

    public List<String> getFilenamesInDirectory(String path){

        List<String> results = new ArrayList<String>();
        File[] files = new File(path).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }

    public List<File> getFilesInDirectory(String path){

        List<File> results = new ArrayList<File>();
        File[] files = new File(path).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.
        for (File file : files) {
            if (file.isFile()) {
                results.add(file);
            }
        }
        return results;
    }

}
