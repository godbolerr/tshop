package com.kalpak.tworkshop.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.kalpak.tworkshop.TshopApp;
import com.kalpak.tworkshop.domain.Info;
import com.kalpak.tworkshop.domain.TResource;
import com.kalpak.tworkshop.repository.TResourceRepository;
import com.kalpak.tworkshop.service.TResourceService;
import com.kalpak.tworkshop.service.dto.TResourceDTO;
import com.kalpak.tworkshop.service.mapper.TResourceMapper;
import com.kalpak.tworkshop.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the TResourceResource REST controller.
 *
 * @see TResourceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TshopApp.class)
public class TResourceResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_MATERIAL = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAL = "BBBBBBBBBB";

    private static final Long DEFAULT_UNIT_PRICE = 1L;
    private static final Long UPDATED_UNIT_PRICE = 2L;

    private static final Long DEFAULT_LENGTH = 1L;
    private static final Long UPDATED_LENGTH = 2L;

    private static final Long DEFAULT_WIDTH = 1L;
    private static final Long UPDATED_WIDTH = 2L;

    private static final Long DEFAULT_HEIGHT = 1L;
    private static final Long UPDATED_HEIGHT = 2L;

    private static final Long DEFAULT_WEIGHT = 1L;
    private static final Long UPDATED_WEIGHT = 2L;

    private static final Long DEFAULT_INNER_ID = 1L;
    private static final Long UPDATED_INNER_ID = 2L;

    private static final Long DEFAULT_OUTER_ID = 1L;
    private static final Long UPDATED_OUTER_ID = 2L;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR = "BBBBBBBBBB";

    @Autowired
    private TResourceRepository tResourceRepository;

    @Autowired
    private TResourceMapper tResourceMapper;

    @Autowired
    private TResourceService tResourceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTResourceMockMvc;

    private TResource tResource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TResourceResource tResourceResource = new TResourceResource(tResourceService);
        this.restTResourceMockMvc = MockMvcBuilders.standaloneSetup(tResourceResource)
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
    public static TResource createEntity() {
    	
 
    	List<Info> infos = new ArrayList<>();
    	infos.add(new Info("city","akola"));
    	infos.add(new Info("state","maharashtra"));
    	
    	
    	
        TResource tResource = new TResource()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .category(DEFAULT_CATEGORY)
            .color(DEFAULT_COLOR)
            .material(DEFAULT_MATERIAL)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .length(DEFAULT_LENGTH)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .innerId(DEFAULT_INNER_ID)
            .outerId(DEFAULT_OUTER_ID)
            .status(DEFAULT_STATUS)
            .vendor(DEFAULT_VENDOR);
        
        tResource.setInfos(infos);
        
        return tResource;
    }

    @Before
    public void initTest() {
        tResourceRepository.deleteAll();
        tResource = createEntity();
    }

    @Test
    public void createTResource() throws Exception {
        int databaseSizeBeforeCreate = tResourceRepository.findAll().size();

        // Create the TResource
        TResourceDTO tResourceDTO = tResourceMapper.toDto(tResource);
        restTResourceMockMvc.perform(post("/api/t-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tResourceDTO)))
            .andExpect(status().isCreated());

        // Validate the TResource in the database
        List<TResource> tResourceList = tResourceRepository.findAll();
        assertThat(tResourceList).hasSize(databaseSizeBeforeCreate + 1);
        TResource testTResource = tResourceList.get(tResourceList.size() - 1);
        assertThat(testTResource.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTResource.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTResource.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testTResource.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testTResource.getMaterial()).isEqualTo(DEFAULT_MATERIAL);
        assertThat(testTResource.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testTResource.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testTResource.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testTResource.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testTResource.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testTResource.getInnerId()).isEqualTo(DEFAULT_INNER_ID);
        assertThat(testTResource.getOuterId()).isEqualTo(DEFAULT_OUTER_ID);
        assertThat(testTResource.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTResource.getVendor()).isEqualTo(DEFAULT_VENDOR);
    }

    @Test
    public void createTResourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tResourceRepository.findAll().size();

        // Create the TResource with an existing ID
        tResource.setId("existing_id");
        TResourceDTO tResourceDTO = tResourceMapper.toDto(tResource);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTResourceMockMvc.perform(post("/api/t-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tResourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TResource> tResourceList = tResourceRepository.findAll();
        assertThat(tResourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTResources() throws Exception {
        // Initialize the database
        tResourceRepository.save(tResource);

        // Get all the tResourceList
        restTResourceMockMvc.perform(get("/api/t-resources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tResource.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR.toString())))
            .andExpect(jsonPath("$.[*].material").value(hasItem(DEFAULT_MATERIAL.toString())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.intValue())))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH.intValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].innerId").value(hasItem(DEFAULT_INNER_ID.intValue())))
            .andExpect(jsonPath("$.[*].outerId").value(hasItem(DEFAULT_OUTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].vendor").value(hasItem(DEFAULT_VENDOR.toString())));
    }

    @Test
    public void getTResource() throws Exception {
        // Initialize the database
        tResourceRepository.save(tResource);

        // Get the tResource
        restTResourceMockMvc.perform(get("/api/t-resources/{id}", tResource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tResource.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR.toString()))
            .andExpect(jsonPath("$.material").value(DEFAULT_MATERIAL.toString()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.intValue()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH.intValue()))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH.intValue()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.intValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.intValue()))
            .andExpect(jsonPath("$.innerId").value(DEFAULT_INNER_ID.intValue()))
            .andExpect(jsonPath("$.outerId").value(DEFAULT_OUTER_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.vendor").value(DEFAULT_VENDOR.toString()));
    }

    @Test
    public void getNonExistingTResource() throws Exception {
        // Get the tResource
        restTResourceMockMvc.perform(get("/api/t-resources/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTResource() throws Exception {
        // Initialize the database
        tResourceRepository.save(tResource);
        int databaseSizeBeforeUpdate = tResourceRepository.findAll().size();

        // Update the tResource
        TResource updatedTResource = tResourceRepository.findOne(tResource.getId());
        updatedTResource
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .category(UPDATED_CATEGORY)
            .color(UPDATED_COLOR)
            .material(UPDATED_MATERIAL)
            .unitPrice(UPDATED_UNIT_PRICE)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .innerId(UPDATED_INNER_ID)
            .outerId(UPDATED_OUTER_ID)
            .status(UPDATED_STATUS)
            .vendor(UPDATED_VENDOR);
        TResourceDTO tResourceDTO = tResourceMapper.toDto(updatedTResource);

        restTResourceMockMvc.perform(put("/api/t-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tResourceDTO)))
            .andExpect(status().isOk());

        // Validate the TResource in the database
        List<TResource> tResourceList = tResourceRepository.findAll();
        assertThat(tResourceList).hasSize(databaseSizeBeforeUpdate);
        TResource testTResource = tResourceList.get(tResourceList.size() - 1);
        assertThat(testTResource.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTResource.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTResource.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTResource.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testTResource.getMaterial()).isEqualTo(UPDATED_MATERIAL);
        assertThat(testTResource.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testTResource.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testTResource.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testTResource.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testTResource.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testTResource.getInnerId()).isEqualTo(UPDATED_INNER_ID);
        assertThat(testTResource.getOuterId()).isEqualTo(UPDATED_OUTER_ID);
        assertThat(testTResource.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTResource.getVendor()).isEqualTo(UPDATED_VENDOR);
    }

    @Test
    public void updateNonExistingTResource() throws Exception {
        int databaseSizeBeforeUpdate = tResourceRepository.findAll().size();

        // Create the TResource
        TResourceDTO tResourceDTO = tResourceMapper.toDto(tResource);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTResourceMockMvc.perform(put("/api/t-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tResourceDTO)))
            .andExpect(status().isCreated());

        // Validate the TResource in the database
        List<TResource> tResourceList = tResourceRepository.findAll();
        assertThat(tResourceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteTResource() throws Exception {
        // Initialize the database
        tResourceRepository.save(tResource);
        int databaseSizeBeforeDelete = tResourceRepository.findAll().size();

        // Get the tResource
        restTResourceMockMvc.perform(delete("/api/t-resources/{id}", tResource.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TResource> tResourceList = tResourceRepository.findAll();
        assertThat(tResourceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TResource.class);
        TResource tResource1 = new TResource();
        tResource1.setId("id1");
        TResource tResource2 = new TResource();
        tResource2.setId(tResource1.getId());
        assertThat(tResource1).isEqualTo(tResource2);
        tResource2.setId("id2");
        assertThat(tResource1).isNotEqualTo(tResource2);
        tResource1.setId(null);
        assertThat(tResource1).isNotEqualTo(tResource2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TResourceDTO.class);
        TResourceDTO tResourceDTO1 = new TResourceDTO();
        tResourceDTO1.setId("id1");
        TResourceDTO tResourceDTO2 = new TResourceDTO();
        assertThat(tResourceDTO1).isNotEqualTo(tResourceDTO2);
        tResourceDTO2.setId(tResourceDTO1.getId());
        assertThat(tResourceDTO1).isEqualTo(tResourceDTO2);
        tResourceDTO2.setId("id2");
        assertThat(tResourceDTO1).isNotEqualTo(tResourceDTO2);
        tResourceDTO1.setId(null);
        assertThat(tResourceDTO1).isNotEqualTo(tResourceDTO2);
    }
}
