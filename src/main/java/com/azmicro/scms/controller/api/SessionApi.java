package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.SessionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface SessionApi {
    @Operation(summary = "Get a session by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SessionDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid session ID supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value = APP_ROOT + "/sessions/{idSession}", produces = MediaType.APPLICATION_JSON_VALUE)
    SessionDto findById(@PathVariable("idSession") Long id);

    @Operation(summary = "Get all sessions",
            description = "Get all sessions from the database",
            tags = {"Session"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = SessionDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Invalid session ID supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping(value= APP_ROOT + "/sessions/all", produces= MediaType.APPLICATION_JSON_VALUE)
    List<SessionDto> findAll();
    @Operation(summary = "Create a new session", description = "Create a new session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SessionDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @PostMapping(value = APP_ROOT+"/sessions/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    SessionDto save(@RequestBody SessionDto sessionDto);

    @DeleteMapping(value = APP_ROOT+"/sessions/delete/{idSession}")
    @Operation(summary = "Supprimer une session",
            description = "Supprimer une session en spécifiant son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session supprimée avec succès"),
            @ApiResponse(responseCode = "400", description = "Identifiant de la session invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    void delete(@PathVariable("idSession") Long id);

    @GetMapping(value = APP_ROOT + "/sessions/grade/{idGrade}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find sessions by grade ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SessionDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    List<SessionDto> findByGradeId(@PathVariable("idGrade") Long gradeId);

}
