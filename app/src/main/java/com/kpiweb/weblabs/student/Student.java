package com.kpiweb.weblabs.student;

import com.kpiweb.weblabs.group.Group;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students",
uniqueConstraints = {
        @UniqueConstraint(name = "student_email_unique",columnNames = "email"),
        @UniqueConstraint(name = "student_phone_unique",columnNames = "phone")
})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @NotNull(message = "Group is required!")
    private Group group;

    @Column(name = "name")
    @NotBlank(message = "Student name is required!")
    @Pattern(regexp = "^$|[А-ЯІЄЇ][а-яієї]{1,}",
            message = "Only language required is Ukrainian.First letter uppercase, other - lower case")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Student surname is required!")
    @Pattern(regexp = "^$|[А-ЯІЄЇ][а-яієї]{1,}",
            message = "Only language required is Ukrainian.First letter uppercase, other - lower case")
    private String surname;

    @Column(name = "email")
    @NotBlank(message = "Student email is required!")
    @Email(message = "Email is invalid.Example: email@gmail.com")
    private String email;

    @Column(name = "phone")
    @NotBlank(message = "Student phone is required!")
    @Pattern(regexp = "^$|\\+38\\(0\\d{2}\\)\\d{3}\\-\\d{2}\\-\\d{2}",message = "Phone is invalid")
    private String phone;

    public Student(Group group, String name, String surname, String email, String phone) {
        this.group = group;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }
}
