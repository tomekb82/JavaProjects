package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Bar;
import com.mycompany.myapp.repository.BarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Bar.
 */
@RestController
@RequestMapping("/api")
public class BarResource {

    private final Logger log = LoggerFactory.getLogger(BarResource.class);

    @Inject
    private BarRepository barRepository;

    /**
     * POST  /bars -> Create a new bar.
     */
    @RequestMapping(value = "/bars",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Bar bar) {
        log.debug("REST request to save Bar : {}", bar);
        barRepository.save(bar);
    }

    /**
     * GET  /bars -> get all the bars.
     */
    @RequestMapping(value = "/bars",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Bar> getAll() {
        log.debug("REST request to get all Bars");
        return barRepository.findAll();
    }

    /**
     * GET  /bars/:id -> get the "id" bar.
     */
    @RequestMapping(value = "/bars/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Bar> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Bar : {}", id);
        Bar bar = barRepository.findOne(id);
        if (bar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bar, HttpStatus.OK);
    }

    /**
     * DELETE  /bars/:id -> delete the "id" bar.
     */
    @RequestMapping(value = "/bars/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Bar : {}", id);
        barRepository.delete(id);
    }
}
