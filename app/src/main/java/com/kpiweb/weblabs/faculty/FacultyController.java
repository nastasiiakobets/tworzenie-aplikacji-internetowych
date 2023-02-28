package com.kpiweb.weblabs.faculty;

import com.kpiweb.weblabs.faculty.Faculty;
import com.kpiweb.weblabs.faculty.FacultyService;
import com.kpiweb.weblabs.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/faculties")
@RestController
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @GetMapping
    public Page<Faculty> getFacultys(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer elementsPerPage,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "name") FacultyService.SortFields sortField
    ){
        return facultyService.getFacultys(page,elementsPerPage,sortDirection,sortField);
    }

    @GetMapping(path = "/search")
    public List<Faculty> searchFacultys(@RequestParam(required = false, defaultValue = "") String name
    ){
        return facultyService.searchFacultysByName(name);
    }

    @GetMapping("/{id}")
    public Faculty getOne(@PathVariable Long id){
        return facultyService.getOneById(id);
    }

    @PostMapping
    public void registerNewFaculty(@RequestBody Faculty Faculty){
        facultyService.addNewFaculty(Faculty);
    }

    @DeleteMapping(path = "/{facultyId}")
    public void deleteFaculty(@PathVariable("facultyId") Long id){
        facultyService.deleteFaculty(id);
    }

    @PutMapping(path = "/{Id}")
    public void updateFaculty(
            @RequestBody Faculty Faculty){
        facultyService.updateFaculty(Faculty);
    }

}