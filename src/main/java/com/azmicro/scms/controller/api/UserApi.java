package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.UserDto;
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

import java.util.List;
import java.util.Optional;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface UserApi {
    @Operation(summary = "Créer un nouvel utilisateur", description = "Créer un nouvel utilisateur avec les informations fournies dans le corps de la requête.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping(value = APP_ROOT+"/users/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto save(@RequestBody UserDto userDto);


    @Operation(summary = "Supprimer un utilisateur par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur supprimé avec succès"),
            @ApiResponse(responseCode = "400", description = "Utilisateur non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping(value = APP_ROOT + "/users/{id}")
    void delete(@PathVariable("id") Long id);


    @GetMapping(value = APP_ROOT + "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer un utilisateur par ID", description = "Permet de récupérer un utilisateur à partir de son ID")
    @ApiResponse(responseCode = "200", description = "L'utilisateur correspondant à l'ID", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "400", description = "L'utilisateur n'existe pas")
    Optional<UserDto> findById(@Parameter(description = "ID de l'utilisateur") @PathVariable("id") Long id);

    @Operation(summary = "Recherche un utilisateur par adresse e-mail",
            description = "Recherche un utilisateur existant dans la base de données par son adresse e-mail.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "400", description = "Aucun utilisateur trouvé pour l'adresse e-mail fournie.")
    })
    @GetMapping(value = APP_ROOT + "/users/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    Optional<UserDto> findByEmail(@PathVariable("email") String email);


    @Operation(summary = "Récupère la liste de tous les utilisateurs", tags = { "utilisateurs" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des utilisateurs retournée",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class))))})
    @GetMapping(value = APP_ROOT + "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> findAll();


    @Operation(summary = "Récupère tous les utilisateurs ayant un certain rôle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des utilisateurs retournée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping(value = APP_ROOT + "/users/role/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> findAllByRole(@PathVariable("roleId") Long roleId);


    @Operation(summary = "Trouver un utilisateur par son email et son mot de passe",
            description = "Retourne un utilisateur correspondant à l'email et au mot de passe fournis.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "L'utilisateur a été trouvé et renvoyé avec succès."),
                    @ApiResponse(responseCode = "400", description = "Aucun utilisateur trouvé avec l'email et/ou le mot de passe fournis."),
                    @ApiResponse(responseCode = "500", description = "Une erreur interne est survenue côté serveur.")
            })
    @GetMapping(value = APP_ROOT + "/users/{email}/{password}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Optional<UserDto> findByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password);


}
