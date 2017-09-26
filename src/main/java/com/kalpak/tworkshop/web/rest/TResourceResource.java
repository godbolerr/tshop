package com.kalpak.tworkshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kalpak.tworkshop.service.TResourceService;
import com.kalpak.tworkshop.web.rest.util.HeaderUtil;
import com.kalpak.tworkshop.web.rest.util.PaginationUtil;
import com.kalpak.tworkshop.service.dto.TResourceDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TResource.
 */
@RestController
@RequestMapping("/api")
public class TResourceResource {

    private final Logger log = LoggerFactory.getLogger(TResourceResource.class);

    private static final String ENTITY_NAME = "tResource";

    private final TResourceService tResourceService;

    public TResourceResource(TResourceService tResourceService) {
        this.tResourceService = tResourceService;
    }

    /**
     * POST  /t-resources : Create a new tResource.
     *
     * @param tResourceDTO the tResourceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tResourceDTO, or with status 400 (Bad Request) if the tResource has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/t-resources")
    @Timed
    public ResponseEntity<TResourceDTO> createTResource(@RequestBody TResourceDTO tResourceDTO) throws URISyntaxException {
        log.debug("REST request to save TResource : {}", tResourceDTO);
        if (tResourceDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tResource cannot already have an ID")).body(null);
        }
        TResourceDTO result = tResourceService.save(tResourceDTO);
        return ResponseEntity.created(new URI("/api/t-resources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /t-resources : Updates an existing tResource.
     *
     * @param tResourceDTO the tResourceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tResourceDTO,
     * or with status 400 (Bad Request) if the tResourceDTO is not valid,
     * or with status 500 (Internal Server Error) if the tResourceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/t-resources")
    @Timed
    public ResponseEntity<TResourceDTO> updateTResource(@RequestBody TResourceDTO tResourceDTO) throws URISyntaxException {
        log.debug("REST request to update TResource : {}", tResourceDTO);
        if (tResourceDTO.getId() == null) {
            return createTResource(tResourceDTO);
        }
        TResourceDTO result = tResourceService.save(tResourceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tResourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /t-resources : get all the tResources.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tResources in body
     */
    @GetMapping("/t-resources")
    @Timed
    public ResponseEntity<List<TResourceDTO>> getAllTResources(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of TResources");
        Page<TResourceDTO> page = tResourceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/t-resources");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /t-resources/:id : get the "id" tResource.
     *
     * @param id the id of the tResourceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tResourceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/t-resources/{id}")
    @Timed
    public ResponseEntity<TResourceDTO> getTResource(@PathVariable String id) {
        log.debug("REST request to get TResource : {}", id);
        TResourceDTO tResourceDTO = tResourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tResourceDTO));
    }

    /**
     * DELETE  /t-resources/:id : delete the "id" tResource.
     *
     * @param id the id of the tResourceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/t-resources/{id}")
    @Timed
    public ResponseEntity<Void> deleteTResource(@PathVariable String id) {
        log.debug("REST request to delete TResource : {}", id);
        tResourceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
