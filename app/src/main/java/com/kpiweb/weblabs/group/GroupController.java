package com.kpiweb.weblabs.group;

import com.kpiweb.weblabs.group.Group;
import com.kpiweb.weblabs.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/groups")
@RestController
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping
    public Page<Group> getGroups(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer elementsPerPage,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "name") GroupService.SortFields sortField
    ){
        return groupService.getGroups(page,elementsPerPage,sortDirection,sortField);
    }

    @GetMapping("/{id}")
    public Group getOne(@PathVariable Long id){
        return groupService.getOneById(id);
    }

    @GetMapping(path = "/search")
    public List<Group> searchGroups(@RequestParam(required = false, defaultValue = "") String name){
        return groupService.searchGroupByName(name);
    }

    @PostMapping
    public void registerNewGroup(@RequestBody Group group){
        groupService.addNewGroup(group);
    }

    @DeleteMapping(path = "/{Id}")
    public void deleteGroup(@PathVariable("Id") Long id){
        groupService.deleteGroup(id);
    }

    @PutMapping(path = "/{Id}")
    public void updateGroup(
            @RequestBody Group group){
        groupService.updateGroup(group);
    }

}
