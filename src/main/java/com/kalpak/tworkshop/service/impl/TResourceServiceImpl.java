package com.kalpak.tworkshop.service.impl;

import com.kalpak.tworkshop.service.TResourceService;
import com.kalpak.tworkshop.domain.Info;
import com.kalpak.tworkshop.domain.TResource;
import com.kalpak.tworkshop.repository.TResourceRepository;
import com.kalpak.tworkshop.service.dto.TResourceDTO;
import com.kalpak.tworkshop.service.mapper.TResourceMapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing TResource.
 */
@Service
public class TResourceServiceImpl implements TResourceService{

    private final Logger log = LoggerFactory.getLogger(TResourceServiceImpl.class);

    private final TResourceRepository tResourceRepository;

    private final TResourceMapper tResourceMapper;

    public TResourceServiceImpl(TResourceRepository tResourceRepository, TResourceMapper tResourceMapper) {
        this.tResourceRepository = tResourceRepository;
        this.tResourceMapper = tResourceMapper;
    }

    /**
     * Save a tResource.
     *
     * @param tResourceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TResourceDTO save(TResourceDTO tResourceDTO) {
        log.debug("Request to save TResource : {}", tResourceDTO);
        TResource tResource = tResourceMapper.toEntity(tResourceDTO);
    	List<Info> infos = new ArrayList<>();
    	infos.add(new Info("city","akola"));
    	infos.add(new Info("state","maharashtra"));
    	tResource.setInfos(infos);
        tResource = tResourceRepository.save(tResource);
        return tResourceMapper.toDto(tResource);
    }

    /**
     *  Get all the tResources.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<TResourceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TResources");
        return tResourceRepository.findAll(pageable)
            .map(tResourceMapper::toDto);
    }

    /**
     *  Get one tResource by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public TResourceDTO findOne(String id) {
        log.debug("Request to get TResource : {}", id);
        TResource tResource = tResourceRepository.findOne(id);
        return tResourceMapper.toDto(tResource);
    }

    /**
     *  Delete the  tResource by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete TResource : {}", id);
        tResourceRepository.delete(id);
    }
}
