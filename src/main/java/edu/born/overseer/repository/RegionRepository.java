package edu.born.overseer.repository;

import edu.born.overseer.model.Region;

import java.util.List;

public interface RegionRepository {

    Region save(Region region);

    // false if not found
    boolean delete(int id);

    Region getById(int id);

    Region getByTitle(String title);

    List<Region> getAll();

}
