package com.kpiweb.weblabs.schedule;

import com.kpiweb.weblabs.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/schedules")
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @GetMapping
    public Page<Schedule> getSchedules(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer elementsPerPage,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "group") ScheduleService.SortFields sortField
    ) {
        return scheduleService.getSchedules(page, elementsPerPage, sortDirection, sortField);
    }

    @GetMapping("/getByGroup")
    public Page<Schedule> getSchedulesByGroup(@RequestParam(required = false, defaultValue = "0") Integer page,
                                              @RequestParam(required = false, defaultValue = "10") Integer elementsPerPage,
                                              @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
                                              @RequestParam(required = false, defaultValue = "group") ScheduleService.SortFields sortField,
                                              @RequestParam(name = "group", required = true ) Long groupId) {
        return scheduleService.getSchedulesByGroup(groupId, page, elementsPerPage, sortDirection, sortField);
    }

    @GetMapping("/{id}")
    public Schedule getOne(@PathVariable Long id){
        return scheduleService.getOneById(id);
    }

    @PostMapping
    public void registerNewSchedule(@RequestBody Schedule schedule) {
        scheduleService.addNewSchedule(schedule);
    }

    @DeleteMapping(path = "/{scheduleId}")
    public void deleteSchedule(@PathVariable("scheduleId") Long id) {
        scheduleService.deleteSchedule(id);
    }

    @PutMapping(path = "/{scheduleId}")
    public void updateSchedule(@RequestBody Schedule schedule) {
        scheduleService.updateSchedule(schedule);
    }

}
