package com.kpiweb.weblabs.faculty;

import com.kpiweb.weblabs.exceptions.CRUDoperationException;
import com.kpiweb.weblabs.faculty.Faculty;
import com.kpiweb.weblabs.faculty.FacultyRepository;
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
public class FacultyService{
    private FacultyRepository facultyRepository;

    public enum SortFields {
        name,surname
    }

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Page<Faculty> getFacultys(
            @Min(value = 0,message = "Page is invalid!") Integer page,
            @Min(value = 1,message = "There can`t be less than 1 element per page!") Integer elementsPerPage,
            Sort.Direction sortDirection, com.kpiweb.weblabs.faculty.FacultyService.SortFields sortFields
    )
    {
        Pageable pageable = PageRequest.of(page,elementsPerPage,Sort.by(sortDirection,sortFields.name()));
        return facultyRepository.findAll(pageable);
    }

    public Faculty getOneById(Long id) {
        Optional<Faculty> result = facultyRepository.findById(id);
        if (!result.isPresent()) {
            throw new CRUDoperationException("There`s no faculty with id: " + id,"id");
        }
        return result.orElse(null);
    }

    public void addNewFaculty(@Valid Faculty faculty) {
        if(facultyRepository.existsByName(faculty.getName())){
            throw new CRUDoperationException("This name is taken!","name");
        } else if (facultyRepository.existsByShortname(faculty.getShortname())) {
            throw new CRUDoperationException("This shortname is taken!","short_name");
        }
        facultyRepository.save(faculty);
    }

    public void deleteFaculty(@Min(value = 1,message = "Id is invalid!") Long id) {
        if(!facultyRepository.existsById(id)){
            throw new CRUDoperationException("There is no faculty with id: " + id,"id");
        }
        facultyRepository.deleteById(id);
    }

    public List<Faculty> searchFacultysByName(@NotNull String name){
        return facultyRepository.findAllByNameOrderByName(name);
    }

    public void updateFaculty(@Valid Faculty faculty) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(faculty.getId());
        if (!facultyOptional.isPresent()) {
            throw new CRUDoperationException("There`s no faculty with id: " + faculty.getId(),"id");
        }
        facultyRepository.save(faculty);
    }
}