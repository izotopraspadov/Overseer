package edu.guap.enclave.repository;

import edu.guap.enclave.model.Region;

import java.util.List;
import java.util.Optional;

public interface RegionRepository {

    Region save(Region region);

    // false if not found
    boolean delete(int id);

    // null if not found
    Region get(int id);

    Optional<Region> getByTitle(String title);

    List<Region> getAll();

}
