package lodalucard90.howtodev.java.spring.openapi.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lodalucard90.howtodev.java.spring.openapi.example.dto.Example;
import lodalucard90.howtodev.java.spring.openapi.example.dto.PagedResponseExample;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("examples")
@Tag(name = "Example", description = "Operations to manage an example.")
public class ExampleController {

    @Operation(
            summary = "Get all the Examples",
            description = "Return a paged list of examples.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A list of examples."
                    )
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResponseExample getAllExamplesV1(@RequestParam(required = false, defaultValue = "0") Integer curPage,
                                                 @RequestParam(required = false, defaultValue = "25") Integer pageSize) {
        return new PagedResponseExample();
    }

    @Operation(
            summary = "New Example",
            description = "Create a new Example.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Example created.",
                            headers = {
                                    @Header(
                                            name = "location",
                                            description = "Location of the created Example",
                                            schema = @Schema(
                                                    type = "string",
                                                    format = "uri",
                                                    example = "http://example.com/examples/{generatedUuid"
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request."
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflicts."
                    )
            }
    )
    @PostMapping
    public void createExampleV1(@RequestBody @Valid Example example) {}

    @Operation(
            summary = "Get an Examples",
            description = "Return a specific examples using its **uuid**.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "An examples."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found."
                    )
            }
    )
    @GetMapping(path = "{uuidExample}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Example getAnExampleByUuidV1(@PathVariable String uuidExample) {
        return new Example();
    }

    @Operation(
            summary = "Update an Examples",
            description = "Update a specific examples using its **uuid**.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Costumer Updated."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found."
                    )
            }
    )
    @PutMapping(path = "{uuidExample}")
    public void updateAnExampleByUuidV1(@PathVariable String uuidExample,
                                        @RequestBody @Valid Example example) { }

    @Operation(
            summary = "Delete an Examples",
            description = "Delete a specific examples using its **uuid**.",
            responses = {
                    @ApiResponse(
                            responseCode = "20",
                            description = "Costumer Deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found."
                    )
            }
    )
    @DeleteMapping(path = "{uuidExample}")
    public void deleteAnExampleByUuidV1(@PathVariable String uuidExample) { }
}
