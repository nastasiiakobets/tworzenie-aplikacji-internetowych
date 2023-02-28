package com.kpiweb.weblabs.faculty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Boolean existsByName(String name);
    Boolean existsByShortname(String shortname);

    List<Faculty> findAllByNameOrderByName(String name);
}
