package lodalucard90.howtodev.java.spring.openapi.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NestedExample {
    @Schema(format = "date", example = "2012-12-12")
    private LocalDate dateExample;

    @Schema(example = "true")
    private boolean booleanExample;
}
