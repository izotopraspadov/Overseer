package edu.born.overseer.web.rest;

import edu.born.overseer.model.Region;
import edu.born.overseer.repository.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        log.info("create region {}", region);
        return regionRepository.save(region);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(Region region, @PathVariable int id) {
        log.info("update region {}", region);
        regionRepository.save(region);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id) {
        log.info("delete region {}", id);
        return regionRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Region getById(@PathVariable int id) {
        log.info("get region {}", id);
        return regionRepository.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Region> getAll() {
        log.info("get all regions");
        return regionRepository.getAll();
    }

    @GetMapping(params = {"title"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Region> getAllByTitle(@RequestParam("title") String title) {
        log.info("get all regions by title {}", title);
        return regionRepository.getAllByTitle(title);
    }

}
