package com.kpiweb.weblabs.schedule;

import com.kpiweb.weblabs.discipline.Discipline;
import com.kpiweb.weblabs.group.Group;
import com.kpiweb.weblabs.teacher.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedules", uniqueConstraints = {
        @UniqueConstraint(name = "unique_schedule",
                columnNames = {"teacher_id", "discipline_id", "group_id", "lessonnumber", "dayofweek"})})
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "teacher_id")
    @ManyToOne
    @NotNull(message = "Teacher is required!")
    private Teacher teacher;

    @JoinColumn(name = "discipline_id")
    @ManyToOne
    @NotNull(message = "Discipline is required!")
    private Discipline discipline;

    @JoinColumn(name = "group_id")
    @ManyToOne
    @NotNull(message = "Group is required!")
    private Group group;

    @Column(name = "classroom")
    @NotBlank(message = "Classroom is required!")
    private String classroom;

    @Column(name = "lessonnumber")
    @NotNull(message = "Lesson number is required!")
    @Min(value = 1, message = "Lesson number must be between 1 and 7")
    @Max(value = 7, message = "Lesson number must be between 1 and 7")
    private Integer lessonnumber;

    @Column(name = "dayofweek")
    @NotNull(message = "Day of week is required!")
    @Min(value = 1, message = "Day of week must be between 1 and 7")
    @Max(value = 7, message = "Day of week must be between 1 and 7")
    private Integer dayofweek;
}
