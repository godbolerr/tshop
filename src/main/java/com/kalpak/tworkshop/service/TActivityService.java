package com.kalpak.tworkshop.service;

import com.kalpak.tworkshop.service.dto.TActivityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TActivity.
 */
public interface TActivityService {

    /**
     * Save a tActivity.
     *
     * @param tActivityDTO the entity to save
     * @return the persisted entity
     */
    TActivityDTO save(TActivityDTO tActivityDTO);

    /**
     *  Get all the tActivities.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<TActivityDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" tActivity.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TActivityDTO findOne(String id);

    /**
     *  Delete the "id" tActivity.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
