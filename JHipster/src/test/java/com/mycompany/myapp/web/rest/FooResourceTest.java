package com.mycompany.myapp.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.Foo;
import com.mycompany.myapp.repository.FooRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FooResource REST controller.
 *
 * @see FooResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class FooResourceTest {

    private static final String DEFAULT_FIRST_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_FIRST_NAME = "UPDATED_TEXT";

    @Inject
    private FooRepository fooRepository;

    private MockMvc restFooMockMvc;

    private Foo foo;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FooResource fooResource = new FooResource();
        ReflectionTestUtils.setField(fooResource, "fooRepository", fooRepository);
        this.restFooMockMvc = MockMvcBuilders.standaloneSetup(fooResource).build();
    }

    @Before
    public void initTest() {
        foo = new Foo();
        foo.setFirstName(DEFAULT_FIRST_NAME);
    }

    @Test
    @Transactional
    public void createFoo() throws Exception {
        // Validate the database is empty
        assertThat(fooRepository.findAll()).hasSize(0);

        // Create the Foo
        restFooMockMvc.perform(post("/api/foos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(foo)))
                .andExpect(status().isOk());

        // Validate the Foo in the database
        List<Foo> foos = fooRepository.findAll();
        assertThat(foos).hasSize(1);
        Foo testFoo = foos.iterator().next();
        assertThat(testFoo.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllFoos() throws Exception {
        // Initialize the database
        fooRepository.saveAndFlush(foo);

        // Get all the foos
        restFooMockMvc.perform(get("/api/foos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(foo.getId().intValue()))
                .andExpect(jsonPath("$.[0].firstName").value(DEFAULT_FIRST_NAME.toString()));
    }

    @Test
    @Transactional
    public void getFoo() throws Exception {
        // Initialize the database
        fooRepository.saveAndFlush(foo);

        // Get the foo
        restFooMockMvc.perform(get("/api/foos/{id}", foo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(foo.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFoo() throws Exception {
        // Get the foo
        restFooMockMvc.perform(get("/api/foos/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFoo() throws Exception {
        // Initialize the database
        fooRepository.saveAndFlush(foo);

        // Update the foo
        foo.setFirstName(UPDATED_FIRST_NAME);
        restFooMockMvc.perform(post("/api/foos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(foo)))
                .andExpect(status().isOk());

        // Validate the Foo in the database
        List<Foo> foos = fooRepository.findAll();
        assertThat(foos).hasSize(1);
        Foo testFoo = foos.iterator().next();
        assertThat(testFoo.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void deleteFoo() throws Exception {
        // Initialize the database
        fooRepository.saveAndFlush(foo);

        // Get the foo
        restFooMockMvc.perform(delete("/api/foos/{id}", foo.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Foo> foos = fooRepository.findAll();
        assertThat(foos).hasSize(0);
    }
}
