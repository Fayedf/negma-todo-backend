package com.negma.Service;

import com.negma.Entity.User;
import com.negma.Security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${app.jwt.expiration-seconds}")
    private Long expirationSeconds;

    final JwtEncoder jwtEncoder;

    public String generateToken(String email) {
      Instant now = Instant.now();

        JwtClaimsSet claimsSet =  JwtClaimsSet.builder()
                .subject(email)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expirationSeconds))
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();


        return jwtEncoder.encode(JwtEncoderParameters.from(header,claimsSet)).getTokenValue();
    }
}
