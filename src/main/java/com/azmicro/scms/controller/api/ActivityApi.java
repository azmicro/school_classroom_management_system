package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.ActivityDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface ActivityApi {

    @PostMapping(value = APP_ROOT+"/activities/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    @Operation(summary = "Create a new activity",
            description = "This operation allows you to create a new activity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The activity is created successfully.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActivityDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    ActivityDto save(@RequestBody ActivityDto activityDto);

    @GetMapping(value =APP_ROOT+"/activities/{idActivity}", produces = MediaType.APPLICATION_JSON_VALUE)
    ActivityDto findById(@PathVariable("idActivity") Long id);

    @GetMapping(value = APP_ROOT+"/activities/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ActivityDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/activities/delete/{idActivity}")
    void deleteById(@PathVariable("idActivity") Long id);

    @GetMapping(value = APP_ROOT+"/activities/{idSemester}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ActivityDto> findBySemesterId(@PathVariable("idSemester") Long semesterId);

    @GetMapping(value = APP_ROOT+"/activities/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ActivityDto> findByWordingContainingIgnoreCase(@PathVariable("keyword") String keyword);

    @GetMapping(value = APP_ROOT+"/activities/{wording}", produces = MediaType.APPLICATION_JSON_VALUE)
    ActivityDto findByWording(@PathVariable("wording") String wording);
}
