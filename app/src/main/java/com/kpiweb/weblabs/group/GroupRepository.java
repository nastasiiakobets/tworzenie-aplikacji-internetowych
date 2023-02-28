package com.kpiweb.weblabs.group;

import com.kpiweb.weblabs.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    List<Group> findAllByName(String name);
    Boolean existsByNameAndDepartment(String name, Department department);
}
