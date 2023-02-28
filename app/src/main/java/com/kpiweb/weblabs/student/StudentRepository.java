package com.kpiweb.weblabs.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findStudentByEmail(String email);
    Optional<Student> findStudentByPhone(String phone);
    List<Student> findAllByNameOrSurnameOrderBySurname(String name,String surname);
}
