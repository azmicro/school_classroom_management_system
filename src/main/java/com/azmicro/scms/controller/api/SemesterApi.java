package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.SemesterDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface SemesterApi {
    @Operation(summary = "Find a semester by ID", description = "Returns the semester corresponding to the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The semester was found and returned."),
            @ApiResponse(responseCode = "400", description = "No semester was found for the given ID."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server.")})
    @GetMapping(value = APP_ROOT + "/semesters/{idSemester}", produces = {MediaType.APPLICATION_JSON_VALUE})
    SemesterDto findById(@PathVariable("idSemester") Long id);

    @Operation(summary = "Get all semesters", responses = {
            @ApiResponse(responseCode = "200", description = "Found all semesters"),
            @ApiResponse(responseCode = "400", description = "No semester was found for the given ID."),
            @ApiResponse(responseCode = "500", description = "Server error occurred")
    })
    @GetMapping(value = APP_ROOT + "/semesters/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<SemesterDto> findAll();


    @Operation(summary = "Créer un nouveau semestre", description = "Cette méthode permet de créer un nouveau semestre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Semestre créé avec succès", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SemesterDto.class))}),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur", content = @Content)})
    @PostMapping(value = APP_ROOT + "/semesters/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    SemesterDto save(@RequestBody SemesterDto semesterDto);

    @Operation(summary = "Delete a semester by ID",
            description = "Delete a semester by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Semester deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid semester ID supplied"),
            @ApiResponse(responseCode = "404", description = "Semester not found"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur", content = @Content)})
    @DeleteMapping(value = APP_ROOT + "/semesters/{idSemester}")
    void delete(@PathVariable("idSemester") Long id);


    @GetMapping(value = APP_ROOT + "/semesters/schoolyear/{idSchoolYear}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiResponse(responseCode = "200", description = "Semesters successfully retrieved")
    @ApiResponse(responseCode = "400", description = "Invalid school year id supplied")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    List<SemesterDto> findBySchoolYearId(@PathVariable("idSchoolYear") Long id);
}
