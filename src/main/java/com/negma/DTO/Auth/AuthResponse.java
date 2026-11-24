package com.negma.DTO.Auth;

import com.negma.Enums.Role;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

   private String accessToken ;

}
