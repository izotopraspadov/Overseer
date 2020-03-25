package edu.born.overseer.repository;

import edu.born.overseer.model.Region;

import java.util.List;
import java.util.Optional;

public interface RegionRepository {

    Region save(Region region);

    // false if not found
    boolean delete(int id);

    // null if not found
    Region getById(int id);

    Optional<Region> getByTitle(String title);

    List<Region> getAll();

}
