package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.ExamDto;
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

public interface ExamApi {
    @GetMapping(value = APP_ROOT+"/exams/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get all exams", description = "Get a list of all exams.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams returned successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ExamDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    List<ExamDto> findAll();


    @GetMapping(value = APP_ROOT + "/exams/{idExam}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find an exam by ID", description = "Get an exam's information by providing its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the exam", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExamDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid exam ID supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ExamDto findById(@PathVariable("idExam") Long id);


    @PostMapping(value = APP_ROOT+"/exams/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam created successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExamDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @RequestBody(description = "The exam information in JSON format", required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExamDto.class)))
    ExamDto save(@RequestBody ExamDto examDto);


    @DeleteMapping(value = APP_ROOT+"/exams/delete/{idExam}")
    @Operation(summary = "Delete an exam by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Exam not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    void deleteById(@PathVariable("idExam") Long id);

    @GetMapping(value = APP_ROOT+"/exams/semester/{idSemester}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams found successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ExamDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    List<ExamDto> findBySemesterId(@PathVariable("idSemester") Long semesterId);

    @GetMapping(value = APP_ROOT+"/exams/exam/{keywords}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExamDto.class))})
    @ApiResponse(responseCode = "400", description = "Bad request.")
    @ApiResponse(responseCode = "500", description = "Internal server error.")
    List<ExamDto> findByWordingContainingIgnoreCase(@PathVariable("keywords") String keyword);
}
