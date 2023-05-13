package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.GradeDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
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

public interface GradeApi {
    @GetMapping(value = APP_ROOT+"/grades/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all grades",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = GradeDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    List<GradeDto> findAll();


    @GetMapping(value = APP_ROOT+"/grades/{idGrade}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a grade by ID", description = "Get a grade by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grade found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    GradeDto findById(@PathVariable("idGrade") Long id) throws EntityNotFoundException;


    @PostMapping(value = APP_ROOT+"/grades/create",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new grade", description = "Create a new grade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grade created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    GradeDto save(@RequestBody GradeDto gradeDto);


    @DeleteMapping(value = APP_ROOT+"/grades/delete/{idGrade}")
    @Operation(summary = "Delete a grade by ID", description = "Delete a grade by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grade deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    void deleteById(@PathVariable("idGrade") Long id);


    @GetMapping(value=APP_ROOT+"/grades/highSchool/{idHighSchool}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get grades by high school ID", description = "Get grades by high school ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grades found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    List<GradeDto> findByHighSchoolId(@PathVariable("idHighSchool") Long highSchoolId);


    @GetMapping(value=APP_ROOT+"/grades/schoolYear/{idSchoolYear}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get grades by school year ID", description = "Get grades by school year ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grades found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    List<GradeDto> findBySchoolYearId(@PathVariable("idSchoolYear") Long schoolYearId);


    @GetMapping(value=APP_ROOT+"/grades/wordingContainingIgnoreCase/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get grades by wording containing a keyword", description = "Get grades by wording containing a keyword")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grades found"),
            @ApiResponse(responseCode = "400", description = "Invalid keyword supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    List<GradeDto> findByWordingContainingIgnoreCase(@PathVariable("keyword") String keyword);


    @GetMapping(value=APP_ROOT+"/grades/numberOfStudentGreaterThan/{numberOfStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get grades by number of students greater than a value", description = "Get grades by number of students greater than a value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grades found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    List<GradeDto> findByNumberOfStudentGreaterThan(@PathVariable("numberOfStudent") int numberOfStudent);





                    }
