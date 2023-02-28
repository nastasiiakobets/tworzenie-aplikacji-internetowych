package com.kpiweb.weblabs;

import com.kpiweb.weblabs.discipline.Discipline;
import com.kpiweb.weblabs.discipline.DisciplineRepository;
import com.kpiweb.weblabs.faculty.Faculty;
import com.kpiweb.weblabs.faculty.FacultyRepository;
import com.kpiweb.weblabs.teacher.Teacher;
import com.kpiweb.weblabs.teacher.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureApplication {

    @Bean
    CommandLineRunner commandLineRunner(TeacherRepository teacherRepository, FacultyRepository facultyRepository, DisciplineRepository disciplineRepository){
        return args ->{
            Teacher teeacher1 = new Teacher(
                    "Ук",
                    "Ук",
                    "email@gmail.com",
                    "+38(099)154-88-90"
            );

            Faculty faculty1 = new Faculty("Факультет інформатики та",
                    "ФІОТ");

            Discipline discipline1 = new Discipline("Вища матемамтика");

            teacherRepository.save(teeacher1);
            facultyRepository.save(faculty1);
            disciplineRepository.save(discipline1);
        };
    }
}
