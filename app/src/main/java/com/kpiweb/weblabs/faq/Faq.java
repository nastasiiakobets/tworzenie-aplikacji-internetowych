package com.kpiweb.weblabs.faq;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "faqs")
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question")
    @NotBlank(message = "Question is required!")
    @Pattern(regexp = "^$|[А-ЯІЄЇа-яієї\\d.?:,\"']{1,}",message = "Only language required is Ukrainian")
    private String question;

    @Column(name = "answer")
    @NotBlank(message = "Answer is required!")
    @Pattern(regexp = "^$|[А-ЯІЄЇа-яієї\\d.?:,\"']{1,}",message = "Only language required is Ukrainian")
    private String answer;

    public Faq(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
