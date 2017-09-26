package com.kalpak.tworkshop.service;

import com.kalpak.tworkshop.service.dto.TResourceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TResource.
 */
public interface TResourceService {

    /**
     * Save a tResource.
     *
     * @param tResourceDTO the entity to save
     * @return the persisted entity
     */
    TResourceDTO save(TResourceDTO tResourceDTO);

    /**
     *  Get all the tResources.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<TResourceDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" tResource.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TResourceDTO findOne(String id);

    /**
     *  Delete the "id" tResource.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
