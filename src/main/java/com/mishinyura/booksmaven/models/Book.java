package com.mishinyura.booksmaven.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Z]\\w+", message = "Incorrect pattern!")
    @NotEmpty(message = "Title should not be empty!")
    @Size(min = 2, max = 50, message = "Title should be between 2 and 50!")
    @Column(name = "title", unique = true, nullable = false)
    private String title;

    public Book(String title) {
        this.title = title;
    }
}
