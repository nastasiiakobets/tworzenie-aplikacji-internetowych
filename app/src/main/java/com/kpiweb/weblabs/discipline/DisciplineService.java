package com.kpiweb.weblabs.discipline;

import com.kpiweb.weblabs.exceptions.CRUDoperationException;
import com.kpiweb.weblabs.discipline.Discipline;
import com.kpiweb.weblabs.discipline.DisciplineRepository;
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
public class DisciplineService{
    private DisciplineRepository disciplineRepository;

    public enum SortFields {
        name,surname
    }

    @Autowired
    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public Page<Discipline> getDisciplines(
            @Min(value = 0,message = "Page is invalid!") Integer page,
            @Min(value = 1,message = "There can`t be less than 1 element per page!") Integer elementsPerPage,
            Sort.Direction sortDirection, com.kpiweb.weblabs.discipline.DisciplineService.SortFields sortFields
    )
    {
        Pageable pageable = PageRequest.of(page,elementsPerPage,Sort.by(sortDirection,sortFields.name()));
        return disciplineRepository.findAll(pageable);
    }

    public Discipline getOneById(Long id) {
        Optional<Discipline> result = disciplineRepository.findById(id);
        if (!result.isPresent()) {
            throw new CRUDoperationException("There`s no discipline with id: " + id,"id");
        }
        return result.orElse(null);
    }

    public void addNewDiscipline(@Valid Discipline discipline) {
        if(disciplineRepository.existsByName(discipline.getName())){
            throw new CRUDoperationException("This name is taken!","name");
        }
        disciplineRepository.save(discipline);
    }

    public void deleteDiscipline(@Min(value = 1,message = "Id is invalid!") Long id) {
        if(!disciplineRepository.existsById(id)){
            throw new CRUDoperationException("There is no discipline with id: " + id,"id");
        }
        disciplineRepository.deleteById(id);
    }

    public List<Discipline> searchDisciplinesByName(@NotNull String name){
        return disciplineRepository.findAllByNameOrderByName(name);
    }

    public void updateDiscipline(@Valid Discipline discipline) {
        Optional<Discipline> disciplineOptional = disciplineRepository.findById(discipline.getId());
        if (!disciplineOptional.isPresent()) {
            throw new CRUDoperationException("There`s no discipline with id: " + discipline.getId(),"id");
        }
        disciplineRepository.save(discipline);
    }
}
