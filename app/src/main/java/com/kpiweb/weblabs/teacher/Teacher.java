package com.kpiweb.weblabs.teacher;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachers",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "student_phone_unique", columnNames = "phone")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Teacher name is required!")
    @Pattern(regexp = "^$|[А-ЯІЄЇ][а-яієї]{1,}",
            message = "Only language required is Ukrainian.First letter uppercase, other - lower case")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Teacher surname is required!")
    @Pattern(regexp = "^$|[А-ЯІЄЇ][а-яієї]{1,}",
            message = "Only language required is Ukrainian.First letter uppercase, other - lower case")
    private String surname;

    @Column(name = "email")
    @NotBlank(message = "Teacher email is required!")
    @Email(message = "Email is invalid.Example: email@gmail.com")
    private String email;

    @Column(name = "phone")
    @NotBlank(message = "Teacher phone is required!")
    @Pattern(regexp = "^$|\\+38\\(0\\d{2}\\)\\d{3}\\-\\d{2}\\-\\d{2}", message = "Phone is invalid")
    private String phone;

    public Teacher(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }
}
