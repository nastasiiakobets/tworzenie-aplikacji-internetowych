package com.kpiweb.weblabs.department;

import com.kpiweb.weblabs.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/departments")
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping
    public Page<Department> getDepartments(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer elementsPerPage,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "name") DepartmentService.SortFields sortField
    ){
        return departmentService.getDepartments(page,elementsPerPage,sortDirection,sortField);
    }

    @GetMapping(path = "/search")
    public List<Department> searchDepartments(@RequestParam(required = false, defaultValue = "") String name

    ){
        return departmentService.searchDepartmentsByName(name);
    }

    @GetMapping("/{id}")
    public Department getOne(@PathVariable Long id){
        return departmentService.getOneById(id);
    }

    @PostMapping
    public void registerNewDepartment(@RequestBody Department department){
        departmentService.addNewDepartment(department);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDepartment(@PathVariable("id") Long id){
        departmentService.deleteDepartment(id);
    }

    @PutMapping(path = "/{id}")
    public void updateDepartment(
            @RequestBody Department department){
        departmentService.updateDepartment(department);
    }

}
