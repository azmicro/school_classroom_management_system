package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.CourseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.Date;
import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface CourseApi {

    @PostMapping(value =APP_ROOT+"/courses/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course created successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CourseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @RequestBody(description = "The course information in JSON format", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CourseDto.class)))
    CourseDto save(@RequestBody CourseDto courseDto);

    @GetMapping(value =APP_ROOT+"/courses/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a course by ID", description = "Retrieve a course by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course found successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CourseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Course not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    CourseDto findById(@Parameter(description = "ID of the course to be obtained. Cannot be empty.", required = true)
                       @PathVariable("courseId") Long id);


    @GetMapping(value =APP_ROOT+"/courses/all")
    @Operation(summary = "Get all courses", description = "Get all the courses available in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of courses returned successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CourseDto.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    List<CourseDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/courses/delete/{courseId}")
    @Operation(summary = "Delete a course by ID", description = "Deletes the course corresponding to the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Course not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    void deleteById(@PathVariable("courseId") Long id);

    @GetMapping(value = APP_ROOT + "/courses/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find courses by date range", description = "Find courses that have a date of course between the given start date and end date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courses found successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CourseDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    List<CourseDto> findByDateOfCourseBetween(
            @Parameter(description = "Start date of the range", required = true)
            @PathVariable("startDate") Date startDate,
            @Parameter(description = "End date of the range", required = true)
            @PathVariable("endDate") Date endDate);


    @GetMapping(value =APP_ROOT+"/courses/grade/{gradeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find courses by grade ID", description = "Return a list of courses based on the grade ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the courses",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CourseDto.class)))}),
            @ApiResponse(responseCode = "400", description = "No courses found for the given grade ID")})
    List<CourseDto> findByGradeId(@PathVariable("gradeId") Long gradeId);

    @GetMapping(value = APP_ROOT+"/courses/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "List of courses found successfully.",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = CourseDto.class)))})
    @ApiResponse(responseCode = "400", description = "Bad request.")
    @ApiResponse(responseCode = "500", description = "Internal server error.")
    List<CourseDto> findByCourseContentContainingIgnoreCase(@PathVariable("keyword") String keyword);

    @GetMapping(value =APP_ROOT+"/courses/{courseDescription}", produces=MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find a course by its description", description = "Returns a course based on its description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course found successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CourseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Course not found.")})
    CourseDto findByCourseDescription(@PathVariable("courseDescription") String courseDescription);
}
