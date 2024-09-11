package org.example.helper.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.helper.global.error.exceptions.UnknownRoleException;
import org.example.helper.global.error.exceptions.UserNotFoundException;
import org.example.helper.global.security.auth.CustomUserDetailService;
import org.example.helper.global.security.auth.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private final CustomUserDetailService customUserDetailService;

    public String generateAccess (String ID, String role) {
        Long now = (new Date()).getTime();
        Date expireAt = new Date(now + jwtProperties.accessExpiration());

        return Jwts.builder()
                .setSubject(ID)
                .claim("role", role)
                .claim("type", "access")
                .setExpiration(expireAt)
                .signWith(SignatureAlgorithm.ES256, jwtProperties.secret())
                .compact();
    }

    public String generateRefresh (String ID, String role) {
        Long now = (new Date()).getTime();
        Date expireAt = new Date(now + jwtProperties.refreshExpiration());

        return Jwts.builder()
                .setSubject(ID)
                .claim("role", role)
                .claim("type", "refresh")
                .setExpiration(expireAt)
                .signWith(SignatureAlgorithm.ES256, jwtProperties.secret())
                .compact();
    }


    public Authentication getAuthorization (String token) {
        Claims claims = getClaim(token);

        UserDetails userDetails = null;
        if (claims.get("role") == Role.student) {
            userDetails = customUserDetailService.loadUserByStudentId(claims.getSubject());
        } else if (claims.get("role") == Role.teacher || claims.get("role") == Role.head) {
            userDetails = customUserDetailService.loadUserByTeacherId(UUID.fromString(claims.getSubject()));
        } else {
            throw UnknownRoleException.EXCEPTION;
        }
        if (userDetails == null) throw new UserNotFoundException();

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims getClaim (String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(jwtProperties.secret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public String getToken (HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.header());

        if (StringUtils.hasText(token) && token.startsWith(jwtProperties.prefix()) && token.length() < 8) {
            token = token.split(" ")[1];
        }

        return token;
    }
}
