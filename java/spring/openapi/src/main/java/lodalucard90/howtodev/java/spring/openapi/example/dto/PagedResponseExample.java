package lodalucard90.howtodev.java.spring.openapi.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PagedResponseExample extends PagedResponse {
    @Schema(minLength = 1, maxLength = 20)
    private List<Example> content;
}
