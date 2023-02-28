package com.kpiweb.weblabs.discipline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline,Long> {

    boolean existsByName(String name);

    List<Discipline> findAllByNameOrderByName(String name);
}

