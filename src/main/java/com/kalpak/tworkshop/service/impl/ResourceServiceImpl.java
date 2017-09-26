package com.kalpak.tworkshop.service.impl;

import com.kalpak.tworkshop.service.ResourceService;
import com.kalpak.tworkshop.domain.Resource;
import com.kalpak.tworkshop.repository.ResourceRepository;
import com.kalpak.tworkshop.service.dto.ResourceDTO;
import com.kalpak.tworkshop.service.mapper.ResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing Resource.
 */
@Service
public class ResourceServiceImpl implements ResourceService{

    private final Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);

    private final ResourceRepository resourceRepository;

    private final ResourceMapper resourceMapper;

    public ResourceServiceImpl(ResourceRepository resourceRepository, ResourceMapper resourceMapper) {
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
    }

    /**
     * Save a resource.
     *
     * @param resourceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ResourceDTO save(ResourceDTO resourceDTO) {
        log.debug("Request to save Resource : {}", resourceDTO);
        Resource resource = resourceMapper.toEntity(resourceDTO);
        resource = resourceRepository.save(resource);
        return resourceMapper.toDto(resource);
    }

    /**
     *  Get all the resources.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<ResourceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Resources");
        return resourceRepository.findAll(pageable)
            .map(resourceMapper::toDto);
    }

    /**
     *  Get one resource by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ResourceDTO findOne(String id) {
        log.debug("Request to get Resource : {}", id);
        Resource resource = resourceRepository.findOne(id);
        return resourceMapper.toDto(resource);
    }

    /**
     *  Delete the  resource by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Resource : {}", id);
        resourceRepository.delete(id);
    }
}
