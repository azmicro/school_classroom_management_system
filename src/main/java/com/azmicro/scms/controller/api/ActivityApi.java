package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.ActivityDto;
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

public interface ActivityApi {

    @PostMapping(value = APP_ROOT+"/activities/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    @Operation(summary = "Create a new activity", description = "This operation allows you to create a new activity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The activity is created successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActivityDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @RequestBody(description = "The activity information in JSON format", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ActivityDto.class)))
    ActivityDto save(@RequestBody ActivityDto activityDto);

    @GetMapping(value =APP_ROOT+"/activities/{idActivity}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find an activity by ID",
            description = "This operation allows you to retrieve an activity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The activity is retrieved successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActivityDto.class))),
            @ApiResponse(responseCode = "400", description = "The activity is not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    ActivityDto findById(@PathVariable("idActivity") Long id);

    @GetMapping(value = APP_ROOT+"/activities/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve all activities",
            description = "This operation allows you to retrieve all activities.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The activities are retrieved successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ActivityDto.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<ActivityDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/activities/delete/{idActivity}")
    @Operation(summary = "Delete an activity by ID",
            description = "This operation allows you to delete an activity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The activity is deleted successfully."),
            @ApiResponse(responseCode = "400", description = "The activity is not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    void deleteById(@PathVariable("idActivity") Long id);

    @GetMapping(value = APP_ROOT+"/activities/semester/{idSemester}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find activities by semester ID", description = "This operation allows you to retrieve activities by their semester ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The activities are retrieved successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ActivityDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<ActivityDto> findBySemesterId(@PathVariable("idSemester") Long semesterId);

    @GetMapping(value = APP_ROOT+"/activities/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find activities by keyword",
            description = "This operation allows you to retrieve a list of activities that contain a given keyword in their wording.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The activities are retrieved successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ActivityDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<ActivityDto> findByWordingContainingIgnoreCase(@PathVariable("keyword") String keyword);

    @GetMapping(value = APP_ROOT+"/activities/{wording}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find an activity by wording", description = "This operation allows you to find an activity by its wording.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The activity is retrieved successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActivityDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    ActivityDto findByWording(@PathVariable("wording") String wording);
}
