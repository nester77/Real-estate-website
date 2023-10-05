package com.nester.Rew.service.dto.user;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private RoleDto role;
}
