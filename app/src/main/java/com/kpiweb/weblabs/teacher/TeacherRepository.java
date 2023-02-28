package com.kpiweb.weblabs.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
    List<Teacher> findAllByNameOrSurnameOrderBySurname(String name, String surname);
}
