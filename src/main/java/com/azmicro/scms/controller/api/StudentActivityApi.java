package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.StudentActivityDto;
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

public interface StudentActivityApi {

    @PostMapping(value=APP_ROOT+"/studentActivities/create", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new studentActivity", description = "Create a new studentActivity")
    @ApiResponse(responseCode="200", description="Student activity created successfully")
    @ApiResponse(responseCode="400", description="Invalid request payload or missing required fields")
    @ApiResponse(responseCode="500", description="Internal server error")
    StudentActivityDto save(@RequestBody StudentActivityDto studentActivityDto);


    @DeleteMapping(value = APP_ROOT+"/studentActivities/delete/{studentActivityId}")
    @Operation(summary = "Delete a student activity by ID", description = "Delete a student activity based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student activity successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied or invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    void delete(@PathVariable("studentActivityId") Long id);

    @GetMapping(value = APP_ROOT+"/studentActivities/{idStudentActivity}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a student activity by ID", description = "Retrieves a student activity from the database based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The student activity was found and retrieved successfully."),
            @ApiResponse(responseCode = "400", description = "The provided ID is invalid or not in the correct format."),
            @ApiResponse(responseCode = "500", description = "An error occurred while retrieving the student activity.")
    })
    StudentActivityDto findById(@PathVariable("idStudentActivity") Long id);

    @GetMapping(value = APP_ROOT+"/studentActivity/student/{idStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Renvoie la liste des activités d'un étudiant donné")
    @ApiResponse(responseCode = "404", description = "Aucune activité trouvée pour l'étudiant avec l'id spécifié")
    List<StudentActivityDto> findByStudentId(@PathVariable("idStudent") Long studentId);


    @Operation(summary = "Find all student activities by activity ID", description = "Returns a list of student activities for the given activity ID", tags = {"student-activities"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the student activities",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = StudentActivityDto.class)))),
            @ApiResponse(responseCode = "404", description = "Student activities not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping(value = APP_ROOT+"/studentActivity/activity/{idActivity}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentActivityDto> findByActivityId(@PathVariable("idActivity") Long activityId);


    @GetMapping(value = APP_ROOT+"/studentActivity/student/{idStudent}/activity/{idActivity}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Renvoie la liste des activités étudiantes pour l'étudiant et l'activité donnés")
    @ApiResponse(responseCode = "500", description = "Une erreur s'est produite du côté serveur")
    List<StudentActivityDto> findByStudentIdAndActivityId(@PathVariable("idStudent") Long studentId, @PathVariable("idActivity") Long activityId);

}
