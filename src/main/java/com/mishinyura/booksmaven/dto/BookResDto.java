package com.mishinyura.booksmaven.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Book DTO")
public final class BookResDto {
    @Schema(description = "Book Id", example = "1", type = "Long")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Book title", example = "Some title", type = "String")
    @JsonProperty("title")
    private String title;
}
