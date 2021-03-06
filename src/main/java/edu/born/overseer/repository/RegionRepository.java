package edu.born.overseer.repository;

import edu.born.overseer.model.Region;

import java.util.List;

public interface RegionRepository {

    Region save(Region region);

    // false if not found
    boolean delete(int id);

    // null if not found
    Region getById(int id);

    List<Region> getAll(Integer page, String title);

    void evictCache();

}
