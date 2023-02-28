package com.kpiweb.weblabs.group;

import com.kpiweb.weblabs.department.Department;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups",
uniqueConstraints = {
        @UniqueConstraint(name = "unique_group_name",columnNames = {"name","department_id"})
})
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "department_id")
    @ManyToOne
    @NotNull(message = "Department is required!")
    private Department department;

    @Column(name = "name")
    @NotBlank(message = "Group name is required!")
    @Pattern(regexp = "^$|[А-ЯІЄЇ]{2,}\\-\\d{1,}",message = "Only Ukrainian required.Name example:ІА-12")
    private String name;

    @Column(name = "course")
    @NotNull(message = "Course is required!")
    @Min(value = 1,message = "Course must be between 1 and 6")
    @Max(value = 6,message = "Course must be between 1 and 6")
    private Integer course;

    public Group(Department department, String name, Integer course) {
        this.department = department;
        this.name = name;
        this.course = course;
    }
}
