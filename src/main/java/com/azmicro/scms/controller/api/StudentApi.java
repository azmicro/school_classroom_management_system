package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.StudentDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import com.azmicro.scms.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

public interface StudentApi {
    @Operation(summary = "Find student by ID", description = "Find a student by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "400", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = APP_ROOT+"/students/{idStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudentDto findById(@PathVariable("idStudent") Long id);


    @GetMapping(value = APP_ROOT + "/students/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a student by their code", description = "Retrieve a student by their unique code.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "400", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    StudentDto findByCode(@PathVariable("code") String code);

    @Operation(summary = "Trouver un étudiant par nom de famille (français)",
            description = "Récupère les informations d'un étudiant en recherchant par son nom de famille en français.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'étudiant correspondant est retourné.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun étudiant correspondant n'a été trouvé.")
    })
    @GetMapping(value = APP_ROOT+"/students/{lastNameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudentDto findByLastNameFr(@PathVariable("lastNameFr") String lastNameFr);


    /**
     * Récupère un étudiant par son nom de famille en arabe
     * @param lastNameAr le nom de famille en arabe de l'étudiant à récupérer
     * @return l'étudiant correspondant au nom de famille donné
     */
    @GetMapping(value = APP_ROOT+"/students/{lastNameAr}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer un étudiant par son nom de famille en arabe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'étudiant correspondant au nom de famille donné", content = @Content(schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun étudiant trouvé avec le nom de famille donné", content = @Content)
    })
    StudentDto findByLastNameAr(@PathVariable("lastNameAr") String lastNameAr);


    /**
     * Récupérer un étudiant par son prénom en français
     *
     * @param firstNameFr le prénom de l'étudiant en français
     * @return l'étudiant correspondant
     */
    @Operation(summary = "Récupérer un étudiant par son prénom en français")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'étudiant a été récupéré avec succès"),
            @ApiResponse(responseCode = "400", description = "Aucun étudiant trouvé avec ce prénom"),
            @ApiResponse(responseCode = "500", description = "Une erreur est survenue lors de la récupération de l'étudiant")
    })
    @GetMapping(value = APP_ROOT+"/students/{firstNameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudentDto findByFirstNameFr(@PathVariable("firstNameFr") String firstNameFr);


    @Operation(summary = "Récupérer un étudiant par son prénom en arabe",
            description = "Permet de récupérer un étudiant en recherchant par son prénom en arabe.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'étudiant a été récupéré avec succès",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Aucun étudiant n'a été trouvé avec ce prénom",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur",
                    content = @Content)
    })
    @GetMapping(value = APP_ROOT+"/students/{firstNameAr}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudentDto findByFirstNameAr(@PathVariable("firstNameAr") String firstNameAr);


    /**
     * Récupère la liste de tous les étudiants
     *
     * @return liste des étudiants
     */
    @Operation(summary = "Récupérer tous les étudiants", description = "Cette méthode permet de récupérer la liste de tous les étudiants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste de tous les étudiants retournée avec succès",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StudentDto.class)))}),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur", content = @Content)})
    @GetMapping(value = APP_ROOT+"/students/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findAll();


    @Operation(summary = "Trouver les étudiants dont le nom de famille contient une chaîne de caractères donnée (en français)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des étudiants correspondants trouvés"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping(value = APP_ROOT+"/students/lastname/{lastNameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findByLastNameFrContaining(@PathVariable("lastNameFr") String lastNameFr);


    @Operation(summary = "Trouver les étudiants dont le prénom contient une chaîne de caractères donnée")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des étudiants correspondants",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StudentDto.class)))}),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping(value = APP_ROOT+"/students/firstname/{firstNameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findByFirstNameFrContaining(@PathVariable("firstNameFr") String firstNameFr);


    @Operation(summary = "Rechercher les étudiants par ID de niveau",
            description = "Renvoie une liste d'étudiants correspondant à l'ID de niveau spécifié.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des étudiants trouvés",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = StudentDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Aucun étudiant trouvé pour l'ID de niveau spécifié",
                    content = @Content)})
    @GetMapping(value = APP_ROOT+"/students/grade/{gradeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findByGradeId(@PathVariable("gradeId") Long gradeId);


    @Operation(summary = "Trouver les étudiants par date de naissance", description = "Retourne une liste d'étudiants correspondant à la date de naissance spécifiée.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste d'étudiants retournée avec succès."),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur.")})
    @GetMapping(value = APP_ROOT+"/students/{dateOfBirth}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findByDateOfBirth(@PathVariable("dateOfBirth") Date dateOfBirth);


    @Operation(summary = "Rechercher les étudiants par leur nom et prénom en français",
            description = "Retourne une liste des étudiants correspondants au nom et prénom en français spécifiés.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des étudiants trouvés"),
            @ApiResponse(responseCode = "400", description = "Aucun étudiant trouvé avec le nom et prénom spécifiés"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping(value = APP_ROOT+"/students/{lastNameFr}/{firstNameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findByLastNameFrAndFirstNameFr(
            @Parameter(description = "Nom de l'étudiant en français") @PathVariable("lastNameFr") String lastNameFr,
            @Parameter(description = "Prénom de l'étudiant en français") @PathVariable("firstNameFr") String firstNameFr);


    @PostMapping(value = APP_ROOT+"/students/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Créer un étudiant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objet étudiant à créer",
                    required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentDto.class)) ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "L'étudiant a été créé avec succès",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StudentDto.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            })
    StudentDto save(@RequestBody StudentDto studentDto);



    @Operation(summary = "Supprimer un étudiant par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Étudiant supprimé avec succès"),
            @ApiResponse(responseCode = "400", description = "Étudiant non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping(value = APP_ROOT + "/students/delete/{studentId}")
    void deleteById(@PathVariable("studentId") Long id);

}
