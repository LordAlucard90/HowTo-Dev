package lodalucard90.howtodev.java.spring.openapi.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PagedResponse {
    @Schema(format = "int32", example = "0")
    private Integer curPage;

    @Schema(format = "int32", example = "10")
    private Integer totalPages;

    @Schema(format = "int32", example = "25")
    private Integer elementsForPage;
}
