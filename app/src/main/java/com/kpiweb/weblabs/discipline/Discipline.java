package com.kpiweb.weblabs.discipline;

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
@Table(name = "disciplines",
uniqueConstraints = {
        @UniqueConstraint(name = "unique_discipline_name",columnNames = "name")
})
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Discipline name is required!")
    @Pattern(regexp = "^$|[А-ЯІЄЇа-яієї\\d.?:,\"'\\s]{1,}",message = "Only language required is Ukrainian")
    private String name;

    public Discipline(String name) {
        this.name = name;
    }
}
