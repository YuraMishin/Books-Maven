package com.mishinyura.booksmaven.entities;

import com.mishinyura.booksmaven.utils.constants.MainConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Z]\\w+", message = "Incorrect pattern!")
    @NotEmpty(message = "Title should not be empty!")
    @NotBlank(message = "Title should not be blank!")
    @Size(min = 2, max = 50, message = "Title should be between 2 and 50!")
    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "enabled", unique = false, nullable = false)
    private boolean enabled;

    @Column(name = "photos", unique = false, nullable = true)
    private String photos;

//    @Transient
//    public String getPhotosImagePath() {
//        if (id == null || photos == null) {
//            return "/img/default-user.png";
//        }
//        return "/" + MainConstants.BOOK_PHOTOS + "/" + this.id + "/" + this.photos;
//    }
}
