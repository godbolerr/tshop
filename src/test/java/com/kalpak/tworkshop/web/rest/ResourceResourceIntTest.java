package com.kalpak.tworkshop.web.rest;

import com.kalpak.tworkshop.TshopApp;

import com.kalpak.tworkshop.domain.Resource;
import com.kalpak.tworkshop.repository.ResourceRepository;
import com.kalpak.tworkshop.service.ResourceService;
import com.kalpak.tworkshop.service.dto.ResourceDTO;
import com.kalpak.tworkshop.service.mapper.ResourceMapper;
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
 * Test class for the ResourceResource REST controller.
 *
 * @see ResourceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TshopApp.class)
public class ResourceResourceIntTest {

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
    private ResourceRepository resourceRepository;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restResourceMockMvc;

    private Resource resource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ResourceResource resourceResource = new ResourceResource(resourceService);
        this.restResourceMockMvc = MockMvcBuilders.standaloneSetup(resourceResource)
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
    public static Resource createEntity() {
        Resource resource = new Resource()
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
        return resource;
    }

    @Before
    public void initTest() {
        resourceRepository.deleteAll();
        resource = createEntity();
    }

    @Test
    public void createResource() throws Exception {
        int databaseSizeBeforeCreate = resourceRepository.findAll().size();

        // Create the Resource
        ResourceDTO resourceDTO = resourceMapper.toDto(resource);
        restResourceMockMvc.perform(post("/api/resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resourceDTO)))
            .andExpect(status().isCreated());

        // Validate the Resource in the database
        List<Resource> resourceList = resourceRepository.findAll();
        assertThat(resourceList).hasSize(databaseSizeBeforeCreate + 1);
        Resource testResource = resourceList.get(resourceList.size() - 1);
        assertThat(testResource.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testResource.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testResource.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testResource.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testResource.getMaterial()).isEqualTo(DEFAULT_MATERIAL);
        assertThat(testResource.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testResource.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testResource.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testResource.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testResource.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testResource.getInnerId()).isEqualTo(DEFAULT_INNER_ID);
        assertThat(testResource.getOuterId()).isEqualTo(DEFAULT_OUTER_ID);
        assertThat(testResource.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testResource.getVendor()).isEqualTo(DEFAULT_VENDOR);
    }

    @Test
    public void createResourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resourceRepository.findAll().size();

        // Create the Resource with an existing ID
        resource.setId("existing_id");
        ResourceDTO resourceDTO = resourceMapper.toDto(resource);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourceMockMvc.perform(post("/api/resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Resource> resourceList = resourceRepository.findAll();
        assertThat(resourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllResources() throws Exception {
        // Initialize the database
        resourceRepository.save(resource);

        // Get all the resourceList
        restResourceMockMvc.perform(get("/api/resources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resource.getId())))
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
    public void getResource() throws Exception {
        // Initialize the database
        resourceRepository.save(resource);

        // Get the resource
        restResourceMockMvc.perform(get("/api/resources/{id}", resource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(resource.getId()))
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
    public void getNonExistingResource() throws Exception {
        // Get the resource
        restResourceMockMvc.perform(get("/api/resources/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateResource() throws Exception {
        // Initialize the database
        resourceRepository.save(resource);
        int databaseSizeBeforeUpdate = resourceRepository.findAll().size();

        // Update the resource
        Resource updatedResource = resourceRepository.findOne(resource.getId());
        updatedResource
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
        ResourceDTO resourceDTO = resourceMapper.toDto(updatedResource);

        restResourceMockMvc.perform(put("/api/resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resourceDTO)))
            .andExpect(status().isOk());

        // Validate the Resource in the database
        List<Resource> resourceList = resourceRepository.findAll();
        assertThat(resourceList).hasSize(databaseSizeBeforeUpdate);
        Resource testResource = resourceList.get(resourceList.size() - 1);
        assertThat(testResource.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testResource.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testResource.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testResource.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testResource.getMaterial()).isEqualTo(UPDATED_MATERIAL);
        assertThat(testResource.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testResource.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testResource.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testResource.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testResource.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testResource.getInnerId()).isEqualTo(UPDATED_INNER_ID);
        assertThat(testResource.getOuterId()).isEqualTo(UPDATED_OUTER_ID);
        assertThat(testResource.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testResource.getVendor()).isEqualTo(UPDATED_VENDOR);
    }

    @Test
    public void updateNonExistingResource() throws Exception {
        int databaseSizeBeforeUpdate = resourceRepository.findAll().size();

        // Create the Resource
        ResourceDTO resourceDTO = resourceMapper.toDto(resource);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restResourceMockMvc.perform(put("/api/resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resourceDTO)))
            .andExpect(status().isCreated());

        // Validate the Resource in the database
        List<Resource> resourceList = resourceRepository.findAll();
        assertThat(resourceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteResource() throws Exception {
        // Initialize the database
        resourceRepository.save(resource);
        int databaseSizeBeforeDelete = resourceRepository.findAll().size();

        // Get the resource
        restResourceMockMvc.perform(delete("/api/resources/{id}", resource.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Resource> resourceList = resourceRepository.findAll();
        assertThat(resourceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Resource.class);
        Resource resource1 = new Resource();
        resource1.setId("id1");
        Resource resource2 = new Resource();
        resource2.setId(resource1.getId());
        assertThat(resource1).isEqualTo(resource2);
        resource2.setId("id2");
        assertThat(resource1).isNotEqualTo(resource2);
        resource1.setId(null);
        assertThat(resource1).isNotEqualTo(resource2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceDTO.class);
        ResourceDTO resourceDTO1 = new ResourceDTO();
        resourceDTO1.setId("id1");
        ResourceDTO resourceDTO2 = new ResourceDTO();
        assertThat(resourceDTO1).isNotEqualTo(resourceDTO2);
        resourceDTO2.setId(resourceDTO1.getId());
        assertThat(resourceDTO1).isEqualTo(resourceDTO2);
        resourceDTO2.setId("id2");
        assertThat(resourceDTO1).isNotEqualTo(resourceDTO2);
        resourceDTO1.setId(null);
        assertThat(resourceDTO1).isNotEqualTo(resourceDTO2);
    }
}
