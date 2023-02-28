package com.kpiweb.weblabs.department;

import com.kpiweb.weblabs.faculty.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    boolean existsByFacultyAndNameAndShortname(Faculty faculty, String name, String shortName);

    List<Department> findAllByNameOrderByName(String name);
}

