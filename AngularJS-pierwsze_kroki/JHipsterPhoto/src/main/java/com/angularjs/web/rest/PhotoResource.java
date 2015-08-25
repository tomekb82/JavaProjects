package com.angularjs.web.rest;

import com.angularjs.repository.impl.PhotoRepositoryImpl;
import com.angularjs.repository.util.FileUtil;
import com.codahale.metrics.annotation.Timed;
import com.angularjs.domain.Photo;
import com.angularjs.repository.PhotoRepository;
import com.angularjs.web.rest.util.HeaderUtil;
import com.angularjs.web.rest.dto.PhotoDTO;
import com.angularjs.web.rest.mapper.PhotoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Photo.
 */
@RestController
@RequestMapping("/api")
public class PhotoResource {

    private static final String IMAGE_DIR = "./src/main/webapp/resources/images/";

    private final Logger log = LoggerFactory.getLogger(PhotoResource.class);

    @Inject
    private PhotoRepository photoRepository;

    @Inject
    private PhotoRepositoryImpl photoRepositoryImpl;

    @Inject
    private PhotoMapper photoMapper;

    @Inject
    FileUtil fileUtil;

    private static boolean init = false;
    /**
     * POST  /photos -> Create a new photo.
     */
    @RequestMapping(value = "/photos",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PhotoDTO> create(@RequestBody PhotoDTO photoDTO) throws URISyntaxException {
        log.debug("REST request to save Photo : {}", photoDTO);
        if (photoDTO.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new photo cannot already have an ID").body(null);
        }
        Photo photo = photoMapper.photoDTOToPhoto(photoDTO);
        Photo result = photoRepository.save(photo);
        return ResponseEntity.created(new URI("/api/photos/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("photo", result.getId().toString()))
                .body(photoMapper.photoToPhotoDTO(result));
    }

    /**
     * PUT  /photos -> Updates an existing photo.
     */
    @RequestMapping(value = "/photos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PhotoDTO> update(@RequestBody PhotoDTO photoDTO) throws URISyntaxException {
        log.debug("REST request to update Photo : {}", photoDTO);
        if (photoDTO.getId() == null) {
            return create(photoDTO);
        }
        Photo photo = photoMapper.photoDTOToPhoto(photoDTO);
        Photo result = photoRepository.save(photo);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("photo", photoDTO.getId().toString()))
                .body(photoMapper.photoToPhotoDTO(result));
    }

    /**
     * GET  /photos -> get all the photos.
     */
    @RequestMapping(value = "/photos",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<PhotoDTO> getAll() {
        log.debug("REST request to get all Photos");
        if(!init){
            initialize();
        //    init = true;
        }
        return photoRepository.findAll().stream()
            .map(photo -> photoMapper.photoToPhotoDTO(photo))
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Set up file names
     */
    public void initialize(){
        log.info("FilesInitializaion: initializing files ...");
        List<File> files = fileUtil.getFilesInDirectory(IMAGE_DIR);

        photoRepository.deleteAll();

        for(File file: files){
            Photo photo = new Photo();
            photo.setName(file.getName());
            photo.setAuthor(file.getName());
            photo.setDate(new Date(file.lastModified()));
            log.info("FilesInitializaion: photo" + photo.getName());
            photoRepository.save(photo);
        }
    }
    /**
     * GET  /photos/:id -> get the "id" photo.
     */
    @RequestMapping(value = "/photos/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PhotoDTO> get(@PathVariable String id) {
        log.debug("REST request to get Photo : {}", id);
        return Optional.ofNullable(photoRepository.findOne(id))
            .map(photoMapper::photoToPhotoDTO)
            .map(photoDTO -> new ResponseEntity<>(
                photoDTO,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /photos/:id -> delete the "id" photo.
     */
    @RequestMapping(value = "/photos/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.debug("REST request to delete Photo : {}", id);
        photoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("photo", id.toString())).build();
    }

    /**
     * GET  /photos/filenames -> get all the photos filenames.
     */
    @RequestMapping(value = "/photos/filenames",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<String> getFilenames() {
        log.debug("REST request to get Photos filenames");
        return photoRepositoryImpl.getFilenames();
    }

    /**
     * GET  /photos/description/:filename -> get description by filename .
     */
    @RequestMapping(value = "/photos/description/{filename}",
        method = RequestMethod.GET,
        headers="Accept=*/*",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<PhotoDTO> getDescription(@PathVariable String filename) {
        log.debug("REST request to get Photos description by filename");

        String nameWithoutExtension = "";
        String[] names;
        List<PhotoDTO> photos = photoRepository.findAll().stream()
            .map(photo -> photoMapper.photoToPhotoDTO(photo))
            .collect(Collectors.toCollection(LinkedList::new));

        for(PhotoDTO photo : photos){
            names = photo.getName().split("\\.");
            if(names.length > 0) {
                nameWithoutExtension = names[0];
            }
            if(nameWithoutExtension.equals(filename)){
                return new ResponseEntity<>(photo,HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
