package com.azmicro.scms;

import com.azmicro.scms.dto.RoleDto;
import com.azmicro.scms.dto.UserDto;
import com.azmicro.scms.services.RoleService;
import com.azmicro.scms.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SchoolClassroomManagementSystemApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SchoolClassroomManagementSystemApplication.class, args);

        // Récupérer le UserService depuis le contexte
        UserService userService = context.getBean(UserService.class);
        RoleService roleService = context.getBean(RoleService.class);
        RoleDto roleDto = RoleDto.builder().id(1L).name("ROLE_ADMIN").build();
        roleService.save(roleDto);
        // Créer un utilisateur
        UserDto userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("password")
                .roleDto(roleDto )
                .build();

        // Sauvegarder l'utilisateur dans la base de données
        UserDto savedUser = userService.save(userDto);
        System.out.println("User saved: " + savedUser);

        // Trouver un utilisateur par son ID
        Optional<UserDto> foundUser = userService.findById(savedUser.getId());
        System.out.println("User found by ID: " + foundUser);

        // Trouver un utilisateur par son email
        foundUser = userService.findByEmail(savedUser.getEmail());
        System.out.println("User found by email: " + foundUser);

        // Trouver un utilisateur par email et mot de passe
        foundUser = userService.findByEmailAndPassword(savedUser.getEmail(), savedUser.getPassword());
        System.out.println("User found by email and password: " + foundUser);


        List<UserDto> allUsers = userService.findAll();
        System.out.println("All users: " + allUsers);

        //userService.delete(savedUser.getId());
        //System.out.println("User deleted");
    }


}
