package com.mishinyura.booksmaven.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
public final class BookResDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
}
