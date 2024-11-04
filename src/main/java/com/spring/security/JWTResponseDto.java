package com.spring.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTResponseDto {

    private String accessToken;
    private String refreshToken;
}
