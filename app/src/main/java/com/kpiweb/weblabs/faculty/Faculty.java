package com.kpiweb.weblabs.faculty;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "faculties",uniqueConstraints = {
        @UniqueConstraint(name = "unique_faculty_name",columnNames = "name"),
        @UniqueConstraint(name = "unique_faculty_shortname",columnNames = "shortname")
})
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Faculty name is required")
    @Pattern(regexp = "^$|[А-ЯІЄЇа-яієї\\s]{1,}",message = "Only Ukrainian required")
    private String name;

    @Column(name = "shortname")
    @NotBlank(message = "Faculty short name is required")
    @Pattern(regexp = "^$|[А-ЯІЄЇ]{1,}",message = "Shortname is invalid.Example: ФІОТ")
    private String shortname;

    public Faculty(String name, String shortname) {
        this.name = name;
        this.shortname = shortname;
    }
}
