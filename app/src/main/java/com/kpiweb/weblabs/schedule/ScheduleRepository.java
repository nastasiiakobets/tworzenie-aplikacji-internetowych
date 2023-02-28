package com.kpiweb.weblabs.schedule;

import com.kpiweb.weblabs.discipline.Discipline;
import com.kpiweb.weblabs.group.Group;
import com.kpiweb.weblabs.teacher.Teacher;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Boolean existsByTeacherAndDisciplineAndGroupAndLessonnumberAndDayofweek(Teacher t, Discipline d,Group g, Integer num1,Integer num2);
    Page<Schedule> findAllByGroup(@NotNull Group group, Pageable pageable);
}