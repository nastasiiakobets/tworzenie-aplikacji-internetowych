package com.kpiweb.weblabs.schedule;

import com.kpiweb.weblabs.exceptions.CRUDoperationException;
import com.kpiweb.weblabs.group.Group;
import com.kpiweb.weblabs.group.GroupService;
import com.kpiweb.weblabs.teacher.Teacher;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    private GroupService groupService;

    public enum SortFields {
        lessonnumber, dayOfWeek, classroom, group
    }

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository,GroupService groupService) {
        this.scheduleRepository = scheduleRepository;
        this.groupService = groupService;
    }

    public Page<Schedule> getSchedules(
            @Min(value = 0, message = "Page is invalid!") Integer page,
            @Min(value = 1, message = "There can`t be less than 1 element per page!") Integer elementsPerPage,
            Sort.Direction sortDirection, ScheduleService.SortFields sortFields
    ) {
        Pageable pageable = PageRequest.of(page, elementsPerPage, Sort.by(sortDirection, sortFields.name()));
        return scheduleRepository.findAll(pageable);
    }

    public Page<Schedule> getSchedulesByGroup(@Min(value = 1, message = "Invalid id!") Long groupId,
                                              @Min(value = 0, message = "Page is invalid!") Integer page,
                                              @Min(value = 1, message = "There can`t be less than 1 element per page!") Integer elementsPerPage,
                                              Sort.Direction sortDirection, ScheduleService.SortFields sortFields) {
        Pageable pageable = PageRequest.of(page, elementsPerPage, Sort.by(sortDirection, sortFields.name()));
        Group group = groupService.getById(groupId);

        return scheduleRepository.findAllByGroup(group,pageable);
    }

    public Schedule getOneById(Long id) {
        Optional<Schedule> result = scheduleRepository.findById(id);
        if (!result.isPresent()) {
            throw new CRUDoperationException("There`s no schedule with id: " + id,"id");
        }
        return result.orElse(null);
    }

    public void addNewSchedule(@Valid Schedule schedule) {
        if (scheduleRepository.existsByTeacherAndDisciplineAndGroupAndLessonnumberAndDayofweek(schedule.getTeacher(),
                schedule.getDiscipline(), schedule.getGroup(), schedule.getLessonnumber(), schedule.getDayofweek())) {
            throw new CRUDoperationException("This schedule already exists!", "group");
        }
        scheduleRepository.save(schedule);
    }

    public void deleteSchedule(@Min(value = 1, message = "Id is invalid!") Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new CRUDoperationException("There is no schedule with id: " + id, "id");
        }
        scheduleRepository.deleteById(id);
    }


    public void updateSchedule(@Valid Schedule schedule) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(schedule.getId());
        if (!scheduleOptional.isPresent()) {
            throw new CRUDoperationException("There`s no schedule with id: " + schedule.getId(), "id");
        }
        scheduleRepository.save(schedule);
    }
}
