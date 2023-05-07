package com.azmicro.scms.handlers;

import com.azmicro.scms.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {
    private final Integer httpCode;
    private final ErrorCodes code;
    private final String message;
    private final List<String> errors;

}
