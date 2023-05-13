package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.HighSchoolDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface HighSchoolApi {

    @Operation(summary = "Get all high schools", description = "Get all high schools")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "High schools found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = APP_ROOT+"/highSchools/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<HighSchoolDto> findAll();

    @Operation(summary = "Get high school by ID", description = "Get high school by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "High school found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "High school not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = APP_ROOT+"/highSchools/{highSchoolId}", produces = MediaType.APPLICATION_JSON_VALUE)
    HighSchoolDto findById(@PathVariable("highSchoolId") Long id);

    @Operation(summary = "Create a new high school", description = "Create a new high school")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "High school created"),
            @ApiResponse(responseCode = "400", description = "Invalid high school data supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = APP_ROOT+"/highSchools/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    HighSchoolDto save(@RequestBody HighSchoolDto highSchoolDto);

    @Operation(summary = "Delete high school by ID", description = "Delete high school by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "High school deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "High school not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = APP_ROOT+"/highSchools/delete/{highSchoolId}")
    void deleteById(@PathVariable("highSchoolId") Long id);

    @Operation(summary = "Find high schools by name (Arabic)", description = "Find high schools by name (Arabic)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "High schools found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = APP_ROOT+"/highSchool/nameAr/{nameAr}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<HighSchoolDto> findByNameAr(@PathVariable("nameAr") String nameAr);

    @Operation(summary = "Find high schools by name (French)", description = "Find high schools by name (French)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "High schools found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = APP_ROOT+"/highSchool/nameFr/{nameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<HighSchoolDto> findByNameFr(@PathVariable("nameFr") String nameFr);

    @Operation(summary = "Find high schools by address", description = "Find high schools by address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "High schools found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = APP_ROOT+"/highSchool/address/{address}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<HighSchoolDto> findByAdress(@PathVariable("address") String address);

    @GetMapping(value = APP_ROOT+"/highSchool/grade/{idGrade}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get high schools by grade ID", description = "Get a list of high schools that have a certain grade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "High schools found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    List<HighSchoolDto> findByGradeId(@PathVariable("idGrade") Long gradeId);

}
