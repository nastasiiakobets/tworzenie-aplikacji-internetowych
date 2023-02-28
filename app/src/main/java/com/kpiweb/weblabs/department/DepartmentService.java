package com.kpiweb.weblabs.department;

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
public class DepartmentService{
    private DepartmentRepository departmentRepository;

    public enum SortFields {
        name,surname
    }

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Page<Department> getDepartments(
            @Min(value = 0,message = "Page is invalid!") Integer page,
            @Min(value = 1,message = "There can`t be less than 1 element per page!") Integer elementsPerPage,
            Sort.Direction sortDirection, com.kpiweb.weblabs.department.DepartmentService.SortFields sortFields
    )
    {
        Pageable pageable = PageRequest.of(page,elementsPerPage,Sort.by(sortDirection,sortFields.name()));
        return departmentRepository.findAll(pageable);
    }

    public Department getOneById(Long id) {
        Optional<Department> result = departmentRepository.findById(id);
        if (!result.isPresent()) {
            throw new CRUDoperationException("There`s no department with id: " + id,"id");
        }
        return result.orElse(null);
    }

    public void addNewDepartment(@Valid Department department) {
        if(departmentRepository.existsByFacultyAndNameAndShortname(department.getFaculty(),department.getName(),department.getShortname())){
            throw new CRUDoperationException("This department already exists!","faculty");
        }
        departmentRepository.save(department);
    }

    public void deleteDepartment(@Min(value = 1,message = "Id is invalid!") Long id) {
        if(!departmentRepository.existsById(id)){
            throw new CRUDoperationException("There is no department with id: " + id,"id");
        }
        departmentRepository.deleteById(id);
    }

    public List<Department> searchDepartmentsByName(@NotNull String name){
        return departmentRepository.findAllByNameOrderByName(name);
    }

    public void updateDepartment(@Valid Department department) {
        Optional<Department> departmentOptional = departmentRepository.findById(department.getId());
        if (!departmentOptional.isPresent()) {
            throw new CRUDoperationException("There`s no department with id: " + department.getId(),"id");
        }
        departmentRepository.save(department);
    }
}
