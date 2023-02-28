package com.kpiweb.weblabs.discipline;

import com.kpiweb.weblabs.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/disciplines")
@RestController
public class DisciplineController {

    private final DisciplineService disciplineService;

    @Autowired
    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }


    @GetMapping
    public Page<Discipline> getDisciplines(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer elementsPerPage,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "name") DisciplineService.SortFields sortField
    ){
        return disciplineService.getDisciplines(page,elementsPerPage,sortDirection,sortField);
    }

    @GetMapping(path = "/search")
    public List<Discipline> searchDisciplines(@RequestParam(required = false, defaultValue = "") String name
    ){
        return disciplineService.searchDisciplinesByName(name);
    }

    @GetMapping("/{id}")
    public Discipline getOne(@PathVariable Long id){
        return disciplineService.getOneById(id);
    }

    @PostMapping
    public void registerNewDiscipline(@RequestBody Discipline discipline){
        disciplineService.addNewDiscipline(discipline);
    }

    @DeleteMapping(path = "/{disciplineId}")
    public void deleteDiscipline(@PathVariable("disciplineId") Long id){
        disciplineService.deleteDiscipline(id);
    }

    @PutMapping(path = "/{Id}")
    public void updateDiscipline(
            @RequestBody Discipline discipline){
        disciplineService.updateDiscipline(discipline);
    }

}