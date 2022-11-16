package com.mishinyura.booksmaven.rest;

import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.dto.ErrorResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Tag(name = "Book controller")
@RequestMapping(BookRestController.BASE_NAME)
public interface BookRestController {
    String MAJOR_VERSION = "api/v1";

    String BASE_NAME = MAJOR_VERSION + "/books";

    @GetMapping()
    @Operation(
            method = "GET",
            summary = "Get all books",
            description = "Get all books"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Books successfully returned",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookResDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Data not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResDto.class)
                    )
            )
    })
    @ResponseStatus(HttpStatus.OK)
    List<BookResDto> getAllBooks();

    @GetMapping("{id}")
    @Operation(
            method = "GET",
            summary = "Get book by id",
            description = "Get book by id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Book successfully returned",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookResDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Data not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResDto.class)
                    )
            )
    })
    @ResponseStatus(HttpStatus.FOUND)
    BookResDto getBookById(@PathVariable Long id);

    @PostMapping("check_unique")
    String checkDuplicateBook(@Param("title") String title);
//
//    /**
//     * Method creates document.
//     *
//     * @param documentDto DocumentDto
//     */
//    @PostMapping()
//    @Operation(
//            method = "POST",
//            summary = "Create document",
//            description = "Create document"
//    )
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "201",
//                    description = "Document successfully created",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = DocumentDto.class)
//                    )
//            ),
//            @ApiResponse(
//                    responseCode = "400",
//                    description = "Bad request",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = ErrorResponseDto.class)
//                    )
//            )
//    })
//    void createDocument(@RequestBody DocumentDto documentDto);
//
//    /**
//     * Method updates document.
//     *
//     * @param documentDto DocumentDto
//     */
//    @PutMapping()
//    @Operation(
//            method = "PUT",
//            summary = "Update document",
//            description = "Update document"
//    )
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Document successfully updated",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = DocumentDto.class)
//                    )
//            ),
//            @ApiResponse(
//                    responseCode = "400",
//                    description = "Bad request",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = ErrorResponseDto.class)
//                    )
//            )
//    })
//    void updateDocument(@RequestBody DocumentDto documentDto);
//
//    /**
//     * Method deletes document by id.
//     *
//     * @param id Id
//     */
//    @DeleteMapping("{id}")
//    @Operation(
//            method = "DELETE",
//            summary = "Delete document by id",
//            description = "Delete document by id"
//    )
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "204",
//                    description = "Document successfully deleted",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = DocumentDto.class)
//                    )
//            ),
//            @ApiResponse(
//                    responseCode = "400",
//                    description = "Bad request",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = ErrorResponseDto.class)
//                    )
//            )
//    })
//    void deleteDocumentById(@PathVariable Long id);
}
