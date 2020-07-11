package lodalucard90.howtodev.java.spring.openapi.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Example {
    @Schema(format = "uuid", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String uuidExample;

    @Schema(example = "My Example")
    private String titleExample;

    @Schema(format = "float", example = "4.2")
    private Float numberExample;

    @Schema(format = "email")
    private String emailExample;

    @Schema(example = "open")
    private StateEnum stateEnum;

    @Schema(example = "red")
    private ColorEnum colorEnum;

    private NestedExample nestedExample;
}
