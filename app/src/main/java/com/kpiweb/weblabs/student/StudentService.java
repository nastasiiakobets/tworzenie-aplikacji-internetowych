package com.kpiweb.weblabs.student;


import com.kpiweb.weblabs.exceptions.CRUDoperationException;
import com.kpiweb.weblabs.teacher.Teacher;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private StudentRepository studentRepository;

    public enum SortFields {
            name,surname
    }

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Page<Student> getStudents(
            @Min(value = 0,message = "Page is invalid!") Integer page,
            @Min(value = 1,message = "There can`t be less than 1 element per page!") Integer elementsPerPage,
            Sort.Direction sortDirection, SortFields sortFields
            )
    {
        Pageable pageable = PageRequest.of(page,elementsPerPage,Sort.by(sortDirection,sortFields.name()));
        return studentRepository.findAll(pageable);
    }

    public Student getOneById(Long id) {
        Optional<Student> result = studentRepository.findById(id);
        if (!result.isPresent()) {
            throw new CRUDoperationException("There`s no student with id: " + id,"id");
        }
        return result.orElse(null);
    }

    public void addNewStudent(@Valid Student student) {
        if(studentRepository.findStudentByEmail(student.getEmail()).isPresent()){
            throw new CRUDoperationException("This email is taken!","email");
        } else if (studentRepository.findStudentByPhone(student.getPhone()).isPresent()) {
            throw new CRUDoperationException("This phone is taken!","phone");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(@Min(value = 1,message = "Id is invalid!") Long id) {
        if(!studentRepository.existsById(id)){
            throw new CRUDoperationException("There is no student with id: " + id,"id");
        }
        studentRepository.deleteById(id);
    }

    public List<Student> searchStudentsByNameAndSurname(@NotNull String name,@NotNull String surname){
        return studentRepository.findAllByNameOrSurnameOrderBySurname(name,surname);
    }

    public void updateStudent(@Valid Student student) {
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if (!studentOptional.isPresent()) {
            throw new CRUDoperationException("There`s no student with id: " + student.getId(),"id");
        }
        studentRepository.save(student);
    }
}
