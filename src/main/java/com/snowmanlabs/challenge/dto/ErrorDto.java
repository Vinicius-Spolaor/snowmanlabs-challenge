package com.snowmanlabs.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorDto {
    private int code;
    private String message;
    private String reason;
}
