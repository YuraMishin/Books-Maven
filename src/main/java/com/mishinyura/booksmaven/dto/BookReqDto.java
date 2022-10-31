package com.mishinyura.booksmaven.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
public class BookReqDto {
    @Pattern(regexp = "[A-Z]\\w+", message = "Incorrect pattern!")
    @NotEmpty(message = "Title should not be empty!")
    @NotBlank(message = "Title should not be blank!")
    @Size(min = 2, max = 50, message = "Title should be between 2 and 50!")
    @JsonProperty("title")
    private String title;
}
