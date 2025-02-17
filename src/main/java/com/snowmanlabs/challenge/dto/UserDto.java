package com.snowmanlabs.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snowmanlabs.challenge.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.id = user.getId();
    }
}
