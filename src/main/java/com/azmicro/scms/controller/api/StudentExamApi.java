package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.StudentExamDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface StudentExamApi {
    @Operation(summary = "Trouver un examen étudiant par ID", description = "Trouver un examen étudiant à partir de son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Examen étudiant trouvé"),
            @ApiResponse(responseCode = "400", description = "Examen étudiant non trouvé")})
    @GetMapping(value = APP_ROOT + "/studentexams/{idStudentExam}", produces = {MediaType.APPLICATION_JSON_VALUE})
    StudentExamDto findById(@PathVariable("idStudentExam") Long id);


    @Operation(summary = "Récupérer la liste de tous les examens des étudiants",
            description = "Cette méthode permet de récupérer la liste de tous les examens des étudiants.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des examens des étudiants récupérée avec succès"),
            @ApiResponse(responseCode = "500", description = "Une erreur est survenue lors de la récupération de la liste des examens des étudiants")
    })
    @GetMapping(value = APP_ROOT + "/studentexams/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<StudentExamDto> findAll();


    @Operation(summary = "Récupère tous les examens passés par un étudiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des examens récupérée avec succès",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StudentExamDto.class))}),
            @ApiResponse(responseCode = "404", description = "Aucun examen trouvé pour l'étudiant spécifié",
                    content = @Content)})
    @GetMapping(value = APP_ROOT + "/studentexams/student/{idStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentExamDto> findByStudentId(@PathVariable("idStudent") Long studentId);


    @Operation(summary = "Trouver les examens d'un étudiant en fonction de l'ID de l'examen",
            description = "Renvoie une liste d'objets StudentExamDto correspondant à l'ID de l'examen donné.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des StudentExamDto correspondant à l'ID de l'examen donné"),
            @ApiResponse(responseCode = "404", description = "Aucun StudentExamDto trouvé pour l'ID de l'examen donné",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur",
                    content = @Content)
    })
    @GetMapping(value = APP_ROOT + "/studentexams/exam/{idExam}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentExamDto> findByExamId(@PathVariable("idExam") Long examId);


    @Operation(summary = "Retourne une liste d'objets StudentExamDto pour un examen et un étudiant donnés",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La liste des objets StudentExamDto est retournée avec succès",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StudentExamDto.class)))),
                    @ApiResponse(responseCode = "404", description = "Aucun objet StudentExamDto trouvé pour l'examen et/ou l'étudiant donné",
                            content = @Content)
            })
    @GetMapping(value = APP_ROOT + "/studentexams/exam/{idExam}/student/{idStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentExamDto> findByExamIdAndStudentId(@PathVariable("idExam") Long examId, @PathVariable("idStudent") Long studentId);


    @Operation(summary = "Récupère une liste d'examens étudiants par date d'examen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste d'examens étudiants trouvée",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StudentExamDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Aucun examen étudiant trouvé pour la date d'examen spécifiée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping(value = APP_ROOT + "/studentexams/{examDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentExamDto> findByExamDate(@PathVariable("examDate") String examDate);


    @Operation(summary = "Récupère la liste des examens corrigés à une date donnée",
            description = "Cette méthode permet de récupérer la liste des examens corrigés à une date donnée.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des examens corrigés a été retournée avec succès"),
            @ApiResponse(responseCode = "400", description = "Aucun examen corrigé n'a été trouvé pour cette date"),
            @ApiResponse(responseCode = "500", description = "Une erreur interne est survenue lors de la recherche des examens corrigés")
    })
    @GetMapping(value = APP_ROOT + "/studentexams/{correctionDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentExamDto> findByCorrectionDate(@Parameter(description = "La date de correction des examens à récupérer") @PathVariable("correctionDate") String correctionDate);


    @Operation(summary = "Trouver les examens des étudiants ayant une note donnée",
            description = "Retourne une liste d'objets StudentExamDto pour les examens des étudiants ayant la note spécifiée")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des examens des étudiants ayant la note spécifiée a été retournée"),
            @ApiResponse(responseCode = "400", description = "Aucun examen d'étudiant n'a été trouvé avec la note spécifiée"),
            @ApiResponse(responseCode = "500", description = "Une erreur s'est produite lors de la recherche des examens des étudiants") })
    @GetMapping(value = APP_ROOT + "/studentexams/{mark}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentExamDto> findByMark(@PathVariable("mark") double mark);


    @Operation(summary = "Créer un examen pour un étudiant", description = "Ajoute un nouvel examen pour un étudiant dans la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Examen créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping(value = APP_ROOT + "/studentexams/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    StudentExamDto save(@RequestBody StudentExamDto studentExamDto);

    @Operation(summary = "Supprimer un examen d'un étudiant par ID",
            description = "Supprime l'examen d'un étudiant à partir de l'ID de l'examen",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Examen d'étudiant supprimé avec succès"),
                    @ApiResponse(responseCode = "400", description = "Examen d'étudiant non trouvé"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            })
    @DeleteMapping(value = APP_ROOT + "/studentexams/delete/{studentExamId}")
    void delete(@PathVariable("studentExamId") Long id);


}
