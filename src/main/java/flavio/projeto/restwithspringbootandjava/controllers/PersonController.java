package flavio.projeto.restwithspringbootandjava.controllers;

import flavio.projeto.restwithspringbootandjava.data.vo.v1.PersonVO;
import flavio.projeto.restwithspringbootandjava.servicos.PersonServices;
import flavio.projeto.restwithspringbootandjava.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// A anotação padrão é a Controller
// A @RestControler nada mais é do que a cokmbinação do anotação
// @Controller e  @ResponseBody


@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People",  description = "EndPoints for Managing People")
public class PersonController {
    @Autowired
    private PersonServices personServices;


    //@RequestMapping(value = "/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/{id}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "Finds all People", description = "Finds all People",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception{
        return personServices.findById(id);
    }

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "Finds a Person", description = "Finds a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    public List<PersonVO> findAll() throws Exception {
        return personServices.findAll();
    }

    @PostMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "Adds a new Person",
            description = "Adds a new Person by passing in a JSON, XML or YML representation of the Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    public PersonVO create(@RequestBody PersonVO person) throws Exception {
        return personServices.create(person);
    }

    @PutMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "Updates a Person",
            description = "Updates a Person by passing in a JSON, XML or YML representation of the Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    public PersonVO update(@RequestBody PersonVO person) throws Exception {
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Person",
            description = "Deletes a Person by passing in a JSON, XML or YML representation of the Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
