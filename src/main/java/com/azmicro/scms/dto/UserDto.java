package com.azmicro.scms.dto;

import com.azmicro.scms.model.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleDto roleDto;
    public static UserDto fromEntity(User user){
        if (user == null) return null;
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roleDto(RoleDto.fromEntity(user.getRole()))
                .build();
    }
    public static User toEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(RoleDto.toEntity(userDto.getRoleDto()));
        return user;
    }
}
