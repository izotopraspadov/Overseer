package edu.guap.enclave.repository;

import edu.guap.enclave.model.Region;

import java.util.List;

public interface RegionRepository {

    Region save(Region region);

    // false if not found
    boolean delete(int id);

    // null if not found
    Region get(int id);

    List<Region> getAll();

}
