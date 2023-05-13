package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.AbsenceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;


public interface AbsenceApi {

    @PostMapping(value = APP_ROOT+"/absences/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new absence", description = "This operation allows you to create a new absence.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The absence is created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid input data."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @RequestBody(description = "The absence information in JSON format", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AbsenceDto.class)))
    AbsenceDto save(@RequestBody AbsenceDto dto);
    @DeleteMapping(value = APP_ROOT+"/absences/delete/{idAbsence}")
    @Operation(summary = "Delete an absence", description = "This operation allows you to delete an absence by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The absence is deleted successfully."),
            @ApiResponse(responseCode = "400", description = "The absence is not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    void delete(@PathVariable("idAbsence") Long id);

    @GetMapping(value = APP_ROOT+"/absences/{idAbsence}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find an absence by ID", description = "This operation allows you to retrieve an absence by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The absence is retrieved successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AbsenceDto.class))),
            @ApiResponse(responseCode = "400", description = "The absence is not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    AbsenceDto findById(@PathVariable("idAbsence") Long id);
    @GetMapping(value = APP_ROOT+"/absences/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve all absences", description = "This operation allows you to retrieve all absences.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The absences are retrieved successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = AbsenceDto.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<AbsenceDto> findAll();
    @GetMapping(value = APP_ROOT+"/absences/student/{idStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve absences by student ID", description = "This operation allows you to retrieve absences by student ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The absences are retrieved successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = AbsenceDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<AbsenceDto> findByStudentId(@PathVariable("idStudent") Long studentId);
    @GetMapping(value = APP_ROOT+"/absences/semester/{idSemester}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve absences by semester ID",
            description = "This operation allows you to retrieve absences by semester ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The absences are retrieved successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = AbsenceDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<AbsenceDto> findBySemesterId(@PathVariable("idSemester") Long semesterId);
    @GetMapping(value = APP_ROOT+"/absences/student/{idStudent}/semester/{idSemester}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve absences by student ID and semester ID",
            description = "This operation allows you to retrieve absences by student ID and semester ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The absences are retrieved successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = AbsenceDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<AbsenceDto> findByStudentAndSemester(@PathVariable("idStudent") Long studentId, @PathVariable("idSemester") Long semesterId);


}
