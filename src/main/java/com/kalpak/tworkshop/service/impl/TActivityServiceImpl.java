package com.kalpak.tworkshop.service.impl;

import com.kalpak.tworkshop.service.TActivityService;
import com.kalpak.tworkshop.domain.TActivity;
import com.kalpak.tworkshop.repository.TActivityRepository;
import com.kalpak.tworkshop.service.dto.TActivityDTO;
import com.kalpak.tworkshop.service.mapper.TActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing TActivity.
 */
@Service
public class TActivityServiceImpl implements TActivityService{

    private final Logger log = LoggerFactory.getLogger(TActivityServiceImpl.class);

    private final TActivityRepository tActivityRepository;

    private final TActivityMapper tActivityMapper;

    public TActivityServiceImpl(TActivityRepository tActivityRepository, TActivityMapper tActivityMapper) {
        this.tActivityRepository = tActivityRepository;
        this.tActivityMapper = tActivityMapper;
    }

    /**
     * Save a tActivity.
     *
     * @param tActivityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TActivityDTO save(TActivityDTO tActivityDTO) {
        log.debug("Request to save TActivity : {}", tActivityDTO);
        TActivity tActivity = tActivityMapper.toEntity(tActivityDTO);
        tActivity = tActivityRepository.save(tActivity);
        return tActivityMapper.toDto(tActivity);
    }

    /**
     *  Get all the tActivities.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<TActivityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TActivities");
        return tActivityRepository.findAll(pageable)
            .map(tActivityMapper::toDto);
    }

    /**
     *  Get one tActivity by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public TActivityDTO findOne(String id) {
        log.debug("Request to get TActivity : {}", id);
        TActivity tActivity = tActivityRepository.findOne(id);
        return tActivityMapper.toDto(tActivity);
    }

    /**
     *  Delete the  tActivity by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete TActivity : {}", id);
        tActivityRepository.delete(id);
    }
}
