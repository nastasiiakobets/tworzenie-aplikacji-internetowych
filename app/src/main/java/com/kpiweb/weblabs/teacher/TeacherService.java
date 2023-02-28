package com.kpiweb.weblabs.teacher;

import com.kpiweb.weblabs.exceptions.CRUDoperationException;
import com.kpiweb.weblabs.group.Group;
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
public class TeacherService{
    private TeacherRepository teacherRepository;

    public enum SortFields {
        name,surname
    }

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Page<Teacher> getTeachers(
            @Min(value = 0,message = "Page is invalid!") Integer page,
            @Min(value = 1,message = "There can`t be less than 1 element per page!") Integer elementsPerPage,
            Sort.Direction sortDirection, TeacherService.SortFields sortFields
    )
    {
        Pageable pageable = PageRequest.of(page,elementsPerPage,Sort.by(sortDirection,sortFields.name()));
        return teacherRepository.findAll(pageable);
    }

    public Teacher getOneById(Long id) {
        Optional<Teacher> result = teacherRepository.findById(id);
        if (!result.isPresent()) {
            throw new CRUDoperationException("There`s no teacher with id: " + id,"id");
        }
        return result.orElse(null);
    }

    public void addNewTeacher(@Valid Teacher teacher) {
        if(teacherRepository.existsByEmail(teacher.getEmail())){
            throw new CRUDoperationException("This email is taken!","email");
        } else if (teacherRepository.existsByPhone(teacher.getPhone())) {
            throw new CRUDoperationException("This phone is taken!","phone");
        }
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(@Min(value = 1,message = "Id is invalid!") Long id) {
        if(!teacherRepository.existsById(id)){
            throw new CRUDoperationException("There is no teacher with id: " + id,"id");
        }
        teacherRepository.deleteById(id);
    }

    public List<Teacher> searchTeachersByNameAndSurname(@NotNull String name, @NotNull String surname){
        return teacherRepository.findAllByNameOrSurnameOrderBySurname(name,surname);
    }

    public void updateTeacher(@Valid Teacher teacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacher.getId());
        if (!teacherOptional.isPresent()) {
            throw new CRUDoperationException("There`s no teacher with id: " + teacher.getId(),"id");
        }
        teacherRepository.save(teacher);
    }
}
