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

    public List<Region> getAll() {
        return regionRepository.getAll();
    }

}
