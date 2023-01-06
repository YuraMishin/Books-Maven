package com.mishinyura.booksmaven.kafka;

import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.dto.ErrorResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Kafka controller")
public class KafkaController {
    private final Producer producer;

    @GetMapping
    @Operation(
            method = "GET",
            summary = "Send Kafka message",
            description = "Send Kafka message"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Message successfully returned"
            )
    })
    @ResponseStatus(HttpStatus.OK)
    public String helloKafka() {
        producer.sendMessage("message to Kafka");

        return "helloKafka rest";
    }
}
