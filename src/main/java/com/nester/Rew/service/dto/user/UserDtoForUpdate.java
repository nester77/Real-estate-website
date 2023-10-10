package com.nester.Rew.service.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDtoForUpdate {
    private Long id;

    @NotEmpty(message = "Email should be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password should be empty")
    @Pattern(regexp = "^[a-zA-z0-9.,;:_?!&%$#@|/]{6,100}$",
            message = "Password should be correct: 6-100 symbols a-z A-z 0-9 . , ; : _ ? ! & % $ # @ | /")
    private String password;

    @NotEmpty(message = "Name should be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 symbols")
    private String firstName;

    @NotEmpty(message = "Last name should be empty")
    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 symbols")
    private String lastName;

    private RoleDto role;
}
