package com.kalpak.tworkshop.web.rest;

import com.kalpak.tworkshop.TshopApp;

import com.kalpak.tworkshop.domain.TActivity;
import com.kalpak.tworkshop.repository.TActivityRepository;
import com.kalpak.tworkshop.service.TActivityService;
import com.kalpak.tworkshop.service.dto.TActivityDTO;
import com.kalpak.tworkshop.service.mapper.TActivityMapper;
import com.kalpak.tworkshop.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TActivityResource REST controller.
 *
 * @see TActivityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TshopApp.class)
public class TActivityResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVE = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_CONCEPT = "AAAAAAAAAA";
    private static final String UPDATED_CONCEPT = "BBBBBBBBBB";

    private static final Long DEFAULT_ACTIVITY_TIME = 1L;
    private static final Long UPDATED_ACTIVITY_TIME = 2L;

    private static final Long DEFAULT_TOTAL_TIME = 1L;
    private static final Long UPDATED_TOTAL_TIME = 2L;

    private static final String DEFAULT_TAGS = "AAAAAAAAAA";
    private static final String UPDATED_TAGS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private TActivityRepository tActivityRepository;

    @Autowired
    private TActivityMapper tActivityMapper;

    @Autowired
    private TActivityService tActivityService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTActivityMockMvc;

    private TActivity tActivity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TActivityResource tActivityResource = new TActivityResource(tActivityService);
        this.restTActivityMockMvc = MockMvcBuilders.standaloneSetup(tActivityResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TActivity createEntity() {
        TActivity tActivity = new TActivity()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .category(DEFAULT_CATEGORY)
            .objective(DEFAULT_OBJECTIVE)
            .concept(DEFAULT_CONCEPT)
            .activityTime(DEFAULT_ACTIVITY_TIME)
            .totalTime(DEFAULT_TOTAL_TIME)
            .tags(DEFAULT_TAGS)
            .status(DEFAULT_STATUS);
        return tActivity;
    }

    @Before
    public void initTest() {
        tActivityRepository.deleteAll();
        tActivity = createEntity();
    }

    @Test
    public void createTActivity() throws Exception {
        int databaseSizeBeforeCreate = tActivityRepository.findAll().size();

        // Create the TActivity
        TActivityDTO tActivityDTO = tActivityMapper.toDto(tActivity);
        restTActivityMockMvc.perform(post("/api/t-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tActivityDTO)))
            .andExpect(status().isCreated());

        // Validate the TActivity in the database
        List<TActivity> tActivityList = tActivityRepository.findAll();
        assertThat(tActivityList).hasSize(databaseSizeBeforeCreate + 1);
        TActivity testTActivity = tActivityList.get(tActivityList.size() - 1);
        assertThat(testTActivity.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTActivity.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTActivity.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testTActivity.getObjective()).isEqualTo(DEFAULT_OBJECTIVE);
        assertThat(testTActivity.getConcept()).isEqualTo(DEFAULT_CONCEPT);
        assertThat(testTActivity.getActivityTime()).isEqualTo(DEFAULT_ACTIVITY_TIME);
        assertThat(testTActivity.getTotalTime()).isEqualTo(DEFAULT_TOTAL_TIME);
        assertThat(testTActivity.getTags()).isEqualTo(DEFAULT_TAGS);
        assertThat(testTActivity.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    public void createTActivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tActivityRepository.findAll().size();

        // Create the TActivity with an existing ID
        tActivity.setId("existing_id");
        TActivityDTO tActivityDTO = tActivityMapper.toDto(tActivity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTActivityMockMvc.perform(post("/api/t-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tActivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TActivity> tActivityList = tActivityRepository.findAll();
        assertThat(tActivityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTActivities() throws Exception {
        // Initialize the database
        tActivityRepository.save(tActivity);

        // Get all the tActivityList
        restTActivityMockMvc.perform(get("/api/t-activities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tActivity.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].objective").value(hasItem(DEFAULT_OBJECTIVE.toString())))
            .andExpect(jsonPath("$.[*].concept").value(hasItem(DEFAULT_CONCEPT.toString())))
            .andExpect(jsonPath("$.[*].activityTime").value(hasItem(DEFAULT_ACTIVITY_TIME.intValue())))
            .andExpect(jsonPath("$.[*].totalTime").value(hasItem(DEFAULT_TOTAL_TIME.intValue())))
            .andExpect(jsonPath("$.[*].tags").value(hasItem(DEFAULT_TAGS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    public void getTActivity() throws Exception {
        // Initialize the database
        tActivityRepository.save(tActivity);

        // Get the tActivity
        restTActivityMockMvc.perform(get("/api/t-activities/{id}", tActivity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tActivity.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.objective").value(DEFAULT_OBJECTIVE.toString()))
            .andExpect(jsonPath("$.concept").value(DEFAULT_CONCEPT.toString()))
            .andExpect(jsonPath("$.activityTime").value(DEFAULT_ACTIVITY_TIME.intValue()))
            .andExpect(jsonPath("$.totalTime").value(DEFAULT_TOTAL_TIME.intValue()))
            .andExpect(jsonPath("$.tags").value(DEFAULT_TAGS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    public void getNonExistingTActivity() throws Exception {
        // Get the tActivity
        restTActivityMockMvc.perform(get("/api/t-activities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTActivity() throws Exception {
        // Initialize the database
        tActivityRepository.save(tActivity);
        int databaseSizeBeforeUpdate = tActivityRepository.findAll().size();

        // Update the tActivity
        TActivity updatedTActivity = tActivityRepository.findOne(tActivity.getId());
        updatedTActivity
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .category(UPDATED_CATEGORY)
            .objective(UPDATED_OBJECTIVE)
            .concept(UPDATED_CONCEPT)
            .activityTime(UPDATED_ACTIVITY_TIME)
            .totalTime(UPDATED_TOTAL_TIME)
            .tags(UPDATED_TAGS)
            .status(UPDATED_STATUS);
        TActivityDTO tActivityDTO = tActivityMapper.toDto(updatedTActivity);

        restTActivityMockMvc.perform(put("/api/t-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tActivityDTO)))
            .andExpect(status().isOk());

        // Validate the TActivity in the database
        List<TActivity> tActivityList = tActivityRepository.findAll();
        assertThat(tActivityList).hasSize(databaseSizeBeforeUpdate);
        TActivity testTActivity = tActivityList.get(tActivityList.size() - 1);
        assertThat(testTActivity.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTActivity.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTActivity.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTActivity.getObjective()).isEqualTo(UPDATED_OBJECTIVE);
        assertThat(testTActivity.getConcept()).isEqualTo(UPDATED_CONCEPT);
        assertThat(testTActivity.getActivityTime()).isEqualTo(UPDATED_ACTIVITY_TIME);
        assertThat(testTActivity.getTotalTime()).isEqualTo(UPDATED_TOTAL_TIME);
        assertThat(testTActivity.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testTActivity.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    public void updateNonExistingTActivity() throws Exception {
        int databaseSizeBeforeUpdate = tActivityRepository.findAll().size();

        // Create the TActivity
        TActivityDTO tActivityDTO = tActivityMapper.toDto(tActivity);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTActivityMockMvc.perform(put("/api/t-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tActivityDTO)))
            .andExpect(status().isCreated());

        // Validate the TActivity in the database
        List<TActivity> tActivityList = tActivityRepository.findAll();
        assertThat(tActivityList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteTActivity() throws Exception {
        // Initialize the database
        tActivityRepository.save(tActivity);
        int databaseSizeBeforeDelete = tActivityRepository.findAll().size();

        // Get the tActivity
        restTActivityMockMvc.perform(delete("/api/t-activities/{id}", tActivity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TActivity> tActivityList = tActivityRepository.findAll();
        assertThat(tActivityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TActivity.class);
        TActivity tActivity1 = new TActivity();
        tActivity1.setId("id1");
        TActivity tActivity2 = new TActivity();
        tActivity2.setId(tActivity1.getId());
        assertThat(tActivity1).isEqualTo(tActivity2);
        tActivity2.setId("id2");
        assertThat(tActivity1).isNotEqualTo(tActivity2);
        tActivity1.setId(null);
        assertThat(tActivity1).isNotEqualTo(tActivity2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TActivityDTO.class);
        TActivityDTO tActivityDTO1 = new TActivityDTO();
        tActivityDTO1.setId("id1");
        TActivityDTO tActivityDTO2 = new TActivityDTO();
        assertThat(tActivityDTO1).isNotEqualTo(tActivityDTO2);
        tActivityDTO2.setId(tActivityDTO1.getId());
        assertThat(tActivityDTO1).isEqualTo(tActivityDTO2);
        tActivityDTO2.setId("id2");
        assertThat(tActivityDTO1).isNotEqualTo(tActivityDTO2);
        tActivityDTO1.setId(null);
        assertThat(tActivityDTO1).isNotEqualTo(tActivityDTO2);
    }
}
