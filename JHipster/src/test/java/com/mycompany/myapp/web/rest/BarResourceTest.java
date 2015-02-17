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
import com.mycompany.myapp.domain.Bar;
import com.mycompany.myapp.repository.BarRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BarResource REST controller.
 *
 * @see BarResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BarResourceTest {

    private static final String DEFAULT_FIELD1 = "SAMPLE_TEXT";
    private static final String UPDATED_FIELD1 = "UPDATED_TEXT";

    @Inject
    private BarRepository barRepository;

    private MockMvc restBarMockMvc;

    private Bar bar;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BarResource barResource = new BarResource();
        ReflectionTestUtils.setField(barResource, "barRepository", barRepository);
        this.restBarMockMvc = MockMvcBuilders.standaloneSetup(barResource).build();
    }

    @Before
    public void initTest() {
        bar = new Bar();
        bar.setField1(DEFAULT_FIELD1);
    }

    @Test
    @Transactional
    public void createBar() throws Exception {
        // Validate the database is empty
        assertThat(barRepository.findAll()).hasSize(0);

        // Create the Bar
        restBarMockMvc.perform(post("/api/bars")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(bar)))
                .andExpect(status().isOk());

        // Validate the Bar in the database
        List<Bar> bars = barRepository.findAll();
        assertThat(bars).hasSize(1);
        Bar testBar = bars.iterator().next();
        assertThat(testBar.getField1()).isEqualTo(DEFAULT_FIELD1);
    }

    @Test
    @Transactional
    public void getAllBars() throws Exception {
        // Initialize the database
        barRepository.saveAndFlush(bar);

        // Get all the bars
        restBarMockMvc.perform(get("/api/bars"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(bar.getId().intValue()))
                .andExpect(jsonPath("$.[0].field1").value(DEFAULT_FIELD1.toString()));
    }

    @Test
    @Transactional
    public void getBar() throws Exception {
        // Initialize the database
        barRepository.saveAndFlush(bar);

        // Get the bar
        restBarMockMvc.perform(get("/api/bars/{id}", bar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(bar.getId().intValue()))
            .andExpect(jsonPath("$.field1").value(DEFAULT_FIELD1.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBar() throws Exception {
        // Get the bar
        restBarMockMvc.perform(get("/api/bars/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBar() throws Exception {
        // Initialize the database
        barRepository.saveAndFlush(bar);

        // Update the bar
        bar.setField1(UPDATED_FIELD1);
        restBarMockMvc.perform(post("/api/bars")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(bar)))
                .andExpect(status().isOk());

        // Validate the Bar in the database
        List<Bar> bars = barRepository.findAll();
        assertThat(bars).hasSize(1);
        Bar testBar = bars.iterator().next();
        assertThat(testBar.getField1()).isEqualTo(UPDATED_FIELD1);
    }

    @Test
    @Transactional
    public void deleteBar() throws Exception {
        // Initialize the database
        barRepository.saveAndFlush(bar);

        // Get the bar
        restBarMockMvc.perform(delete("/api/bars/{id}", bar.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Bar> bars = barRepository.findAll();
        assertThat(bars).hasSize(0);
    }
}
