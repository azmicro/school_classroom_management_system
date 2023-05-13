package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.SchoolYearDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface SchoolYearApi {
    @GetMapping(value = APP_ROOT+"/schoolyears/{idSchoolYear}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find school year by ID", description = "Get a single school year by its ID")
    @ApiResponse(responseCode = "200", description = "School year found")
    @ApiResponse(responseCode = "404", description = "School year not found")
    SchoolYearDto findById(@PathVariable("idSchoolYear") Long id);

    @Operation(summary = "Get all school years", description = "Get a list of all school years")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of school years", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = SchoolYearDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = APP_ROOT+"/schoolyears/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SchoolYearDto> findAll();

    @GetMapping(value = APP_ROOT+"/schoolyears/{wording}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find school year by wording", description = "Find school year by its wording")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "School year found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    SchoolYearDto findByWording(@PathVariable("wording") String wording);


    @GetMapping(value = APP_ROOT+"/schoolyears/{startDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get school years starting after a date", description = "Get a list of school years starting after a specific date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "School years found", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SchoolYearDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid start date provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    List<SchoolYearDto> findAllByStartDateGreaterThanEqual(@PathVariable("startDate") Date startDate);


    @Operation(summary = "Trouver toutes les années scolaires dont la date de fin est antérieure ou égale à la date donnée",
            description = "Renvoie une liste d'objets SchoolYearDto correspondant à toutes les années scolaires dont la date de fin est antérieure ou égale à la date donnée.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des années scolaires retournée avec succès"),
                    @ApiResponse(responseCode = "400", description = "Mauvaise syntaxe de la requête"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            })
    @GetMapping(value = APP_ROOT+"/schoolyears/end-date/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SchoolYearDto> findAllByEndDateLessThanEqual(@PathVariable("endDate") Date endDate);


    @Operation(summary = "Create a new school year")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "School year created successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SchoolYearDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping(value = APP_ROOT+"/schoolyears/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    SchoolYearDto save(@RequestBody SchoolYearDto schoolYearDto);

    @Operation(summary = "Delete a school year by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "School year deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid school year ID supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error while deleting school year")
    })
    @DeleteMapping(value = APP_ROOT+"/schoolyears/delete/{idSchoolYear}")
    void delete(@PathVariable("idSchoolYear") Long id);

}
