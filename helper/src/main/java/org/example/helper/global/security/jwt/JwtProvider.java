package org.example.helper.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.helper.global.security.auth.CustomUserDetailService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private final CustomUserDetailService customUserDetailService;

    public String generateAccess (String studentId, String grade, String classroom, String role) {
        Long now = (new Date()).getTime();
        Date expireAt = new Date(now + jwtProperties.accessExpiration());

        return Jwts.builder()
                .setSubject(studentId)
                .claim("grade", studentId)
                .claim("classroom", studentId)
                .claim("role", studentId)
                .claim("type", "access")
                .setExpiration(expireAt)
                .signWith(SignatureAlgorithm.ES256, jwtProperties.secret())
                .compact();
    }

    public String generateRefresh (String studentId) {
        Long now = (new Date()).getTime();
        Date expireAt = new Date(now + jwtProperties.refreshExpiration());

        return Jwts.builder()
                .setSubject(studentId)
                .claim("type", "refresh")
                .setExpiration(expireAt)
                .signWith(SignatureAlgorithm.ES256, jwtProperties.secret())
                .compact();
    }


    // todo: 여기도 userEntity 완성되면 로직 약간 바꿔야함
    public Authentication getAuthorization (String token) {
        Claims claims = getClaim(token);
        UserDetails userDetails = customUserDetailService.loadUserByUsername(claims.getSubject());
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
