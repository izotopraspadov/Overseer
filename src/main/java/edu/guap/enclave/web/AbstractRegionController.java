package edu.guap.enclave.web;

import edu.guap.enclave.model.Region;
import edu.guap.enclave.repository.RegionRepository;
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
