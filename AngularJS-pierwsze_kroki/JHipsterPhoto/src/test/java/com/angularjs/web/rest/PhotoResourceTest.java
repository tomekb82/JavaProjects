package com.angularjs.web.rest;

import com.angularjs.Application;
import com.angularjs.domain.Photo;
import com.angularjs.repository.PhotoRepository;
import com.angularjs.web.rest.dto.PhotoDTO;
import com.angularjs.web.rest.mapper.PhotoMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the PhotoResource REST controller.
 *
 * @see PhotoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PhotoResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final List<String> DEFAULT_OPINIONS = new ArrayList<String>(Arrays.asList("SAMPLE_TEXT"));
    private static final List<String> UPDATED_OPINIONS = new ArrayList<String>(Arrays.asList("UPDATED_TEXT"));

    @Inject
    private PhotoRepository photoRepository;

    @Inject
    private PhotoMapper photoMapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc restPhotoMockMvc;

    private Photo photo;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PhotoResource photoResource = new PhotoResource();
        ReflectionTestUtils.setField(photoResource, "photoRepository", photoRepository);
        ReflectionTestUtils.setField(photoResource, "photoMapper", photoMapper);
        this.restPhotoMockMvc = MockMvcBuilders.standaloneSetup(photoResource).setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        photoRepository.deleteAll();
        photo = new Photo();
        photo.setName(DEFAULT_NAME);
        photo.setOpinions(DEFAULT_OPINIONS);
    }

    @Test
    public void createPhoto() throws Exception {
        int databaseSizeBeforeCreate = photoRepository.findAll().size();

        // Create the Photo
        PhotoDTO photoDTO = photoMapper.photoToPhotoDTO(photo);

        restPhotoMockMvc.perform(post("/api/photos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(photoDTO)))
                .andExpect(status().isCreated());

        // Validate the Photo in the database
        List<Photo> photos = photoRepository.findAll();
        assertThat(photos).hasSize(databaseSizeBeforeCreate + 1);
        Photo testPhoto = photos.get(photos.size() - 1);
        assertThat(testPhoto.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPhoto.getOpinions()).isEqualTo(DEFAULT_OPINIONS);
    }

    @Test
    public void getAllPhotos() throws Exception {
        // Initialize the database
        photoRepository.save(photo);

        // Get all the photos
        restPhotoMockMvc.perform(get("/api/photos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(photo.getId())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].opinions").value(hasItem(DEFAULT_OPINIONS.toString())));
    }

    @Test
    public void getPhoto() throws Exception {
        // Initialize the database
        photoRepository.save(photo);

        // Get the photo
        restPhotoMockMvc.perform(get("/api/photos/{id}", photo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(photo.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.opinions").value(DEFAULT_OPINIONS.toString()));
    }

    @Test
    public void getNonExistingPhoto() throws Exception {
        // Get the photo
        restPhotoMockMvc.perform(get("/api/photos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updatePhoto() throws Exception {
        // Initialize the database
        photoRepository.save(photo);

		int databaseSizeBeforeUpdate = photoRepository.findAll().size();

        // Update the photo
        photo.setName(UPDATED_NAME);
        photo.setOpinions(UPDATED_OPINIONS);

        PhotoDTO photoDTO = photoMapper.photoToPhotoDTO(photo);

        restPhotoMockMvc.perform(put("/api/photos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(photoDTO)))
                .andExpect(status().isOk());

        // Validate the Photo in the database
        List<Photo> photos = photoRepository.findAll();
        assertThat(photos).hasSize(databaseSizeBeforeUpdate);
        Photo testPhoto = photos.get(photos.size() - 1);
        assertThat(testPhoto.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPhoto.getOpinions()).isEqualTo(UPDATED_OPINIONS);
    }

    @Test
    public void deletePhoto() throws Exception {
        // Initialize the database
        photoRepository.save(photo);

		int databaseSizeBeforeDelete = photoRepository.findAll().size();

        // Get the photo
        restPhotoMockMvc.perform(delete("/api/photos/{id}", photo.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Photo> photos = photoRepository.findAll();
        assertThat(photos).hasSize(databaseSizeBeforeDelete - 1);
    }
}
