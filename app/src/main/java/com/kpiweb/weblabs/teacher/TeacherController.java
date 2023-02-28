package com.kpiweb.weblabs.teacher;

import com.kpiweb.weblabs.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/teachers")
@RestController
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public Page<Teacher> getTeachers(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer elementsPerPage,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "name") TeacherService.SortFields sortField
    ){
        return teacherService.getTeachers(page,elementsPerPage,sortDirection,sortField);
    }

    @GetMapping(path = "/search")
    public List<Teacher> searchTeachers(@RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false, defaultValue = "") String surname
    ){
        return teacherService.searchTeachersByNameAndSurname(name,surname);
    }

    @GetMapping("/{id}")
    public Teacher getOne(@PathVariable Long id){
        return teacherService.getOneById(id);
    }

    @PostMapping
    public void registerNewTeacher(@RequestBody Teacher teacher){
        teacherService.addNewTeacher(teacher);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTeacher(@PathVariable("id") Long id){
        teacherService.deleteTeacher(id);
    }

    @PutMapping(path = "/{id}")
    public void updateTeacher(
            @RequestBody Teacher teacher){
        teacherService.updateTeacher(teacher);
    }

}
