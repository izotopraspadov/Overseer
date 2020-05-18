package edu.born.overseer.web;

import edu.born.overseer.model.Region;
import edu.born.overseer.repository.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractRegionController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RegionRepository regionRepository;

    public Region create(Region region) {
        log.info("create region {}", region);
        return regionRepository.save(region);
    }

    public Region update(Region region, int id) {
        log.info("update region {}", region);
        return regionRepository.save(region);
    }

    public boolean delete(int id) {
        log.info("delete region {}", id);
        return regionRepository.delete(id);
    }

    public Region getById(int id) {
        log.info("get region {}", id);
        return regionRepository.getById(id);
    }

    public List<Region> getAll() {
        log.info("get all regions");
        return regionRepository.getAll();
    }

    public List<Region> getAllByTitle(String title) {
        log.info("get all regions by title {}", title);
        return regionRepository.getAllByTitle(title);
    }

}
