package com.kalpak.tworkshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kalpak.tworkshop.service.TActivityService;
import com.kalpak.tworkshop.web.rest.util.HeaderUtil;
import com.kalpak.tworkshop.web.rest.util.PaginationUtil;
import com.kalpak.tworkshop.service.dto.TActivityDTO;
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
 * REST controller for managing TActivity.
 */
@RestController
@RequestMapping("/api")
public class TActivityResource {

    private final Logger log = LoggerFactory.getLogger(TActivityResource.class);

    private static final String ENTITY_NAME = "tActivity";

    private final TActivityService tActivityService;

    public TActivityResource(TActivityService tActivityService) {
        this.tActivityService = tActivityService;
    }

    /**
     * POST  /t-activities : Create a new tActivity.
     *
     * @param tActivityDTO the tActivityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tActivityDTO, or with status 400 (Bad Request) if the tActivity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/t-activities")
    @Timed
    public ResponseEntity<TActivityDTO> createTActivity(@RequestBody TActivityDTO tActivityDTO) throws URISyntaxException {
        log.debug("REST request to save TActivity : {}", tActivityDTO);
        if (tActivityDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tActivity cannot already have an ID")).body(null);
        }
        TActivityDTO result = tActivityService.save(tActivityDTO);
        return ResponseEntity.created(new URI("/api/t-activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /t-activities : Updates an existing tActivity.
     *
     * @param tActivityDTO the tActivityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tActivityDTO,
     * or with status 400 (Bad Request) if the tActivityDTO is not valid,
     * or with status 500 (Internal Server Error) if the tActivityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/t-activities")
    @Timed
    public ResponseEntity<TActivityDTO> updateTActivity(@RequestBody TActivityDTO tActivityDTO) throws URISyntaxException {
        log.debug("REST request to update TActivity : {}", tActivityDTO);
        if (tActivityDTO.getId() == null) {
            return createTActivity(tActivityDTO);
        }
        TActivityDTO result = tActivityService.save(tActivityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tActivityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /t-activities : get all the tActivities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tActivities in body
     */
    @GetMapping("/t-activities")
    @Timed
    public ResponseEntity<List<TActivityDTO>> getAllTActivities(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of TActivities");
        Page<TActivityDTO> page = tActivityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/t-activities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /t-activities/:id : get the "id" tActivity.
     *
     * @param id the id of the tActivityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tActivityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/t-activities/{id}")
    @Timed
    public ResponseEntity<TActivityDTO> getTActivity(@PathVariable String id) {
        log.debug("REST request to get TActivity : {}", id);
        TActivityDTO tActivityDTO = tActivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tActivityDTO));
    }

    /**
     * DELETE  /t-activities/:id : delete the "id" tActivity.
     *
     * @param id the id of the tActivityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/t-activities/{id}")
    @Timed
    public ResponseEntity<Void> deleteTActivity(@PathVariable String id) {
        log.debug("REST request to delete TActivity : {}", id);
        tActivityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
