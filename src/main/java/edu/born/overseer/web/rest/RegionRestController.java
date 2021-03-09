package edu.born.overseer.web.rest;

import edu.born.overseer.model.Region;
import edu.born.overseer.repository.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RegionRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegionRestController {

    public static final String REST_URL = "/rest/regions";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RegionRepository regionRepository;

    public RegionRestController(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Region create(@RequestBody Region region) {
        checkNew(region);
        log.info("create region {}", region);
        return regionRepository.save(region);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Region region,
                       @PathVariable int id) {
        assureIdConsistent(region, id);
        log.info("update region {}", region);
        regionRepository.save(region);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete region {}", id);
        regionRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Region getById(@PathVariable int id) {
        log.info("get region {}", id);
        return regionRepository.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Region> getAll(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "title", required = false) String title) {
        log.info("get all regions");
        return regionRepository.getAll(page, title);
    }
}
