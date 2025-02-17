package com.snowmanlabs.challenge.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDto {
    private String token;
    private Long expiresIn;
}
