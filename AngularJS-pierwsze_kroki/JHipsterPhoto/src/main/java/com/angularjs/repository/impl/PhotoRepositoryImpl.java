package com.angularjs.repository.impl;

import com.angularjs.domain.Photo;
import com.angularjs.repository.PhotoRepository;
import com.angularjs.repository.util.FileUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by tomek on 25.08.15.
 */

public class PhotoRepositoryImpl{

    private static final String IMAGE_DIR = "./src/main/webapp/resources/images/";

    @Inject
    FileUtil fileUtil;

    public List<String> getFilenames(){
        List<String> files = fileUtil.getFilenamesInDirectory(IMAGE_DIR);
        Collections.sort(files);
        return files;
    }


}
