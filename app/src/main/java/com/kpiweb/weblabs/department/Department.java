package com.kpiweb.weblabs.department;

import com.kpiweb.weblabs.faculty.Faculty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments",
uniqueConstraints = {
        @UniqueConstraint(name = "unique_department",columnNames = {"faculty_id","name","shortname"})
})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @NotNull(message = "Faculty is required")
    private Faculty faculty;

    @Column(name = "name")
    @NotBlank(message = "Department name is required")
    @Pattern(regexp = "^$|[А-ЯІЄЇа-яієї\\s]{1,}",message = "Only Ukrainian required")
    private String name;

    @Column(name = "shortname")
    @NotBlank(message = "Department short name is required")
    @Pattern(regexp = "^$|[А-ЯІЄЇ]{1,}",message = "Shortname is invalid.Example: ІСТ")
    private String shortname;

    public Department(Faculty faculty, String name, String shortname) {
        this.faculty = faculty;
        this.name = name;
        this.shortname = shortname;
    }
}
