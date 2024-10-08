package com.stb.politik.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.stb.politik.user.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {


    // esta clase intercepta todas las peticiones http y verifica si el token es válido

    @Autowired
    private JwtService jwtService;

    // @Autowired
    // private UserService userService;
    private final UserService userService;

    public JwtFilter(UserService userService) {
        this.userService = userService;
    }

    // @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    //         throws ServletException, IOException {

    //     String authHeader = request.getHeader("Authorization");
    //     String token = null;
    //     String username = null;
    //     if (authHeader != null && authHeader.startsWith("Bearer ")) {
    //         token = authHeader.substring(7);
    //         username = jwtService.extractUsername(token);
    //     }
    //     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    //         UserDetails userDetails = userService.loadUserByUsername(username);
    //         if (jwtService.validateToken(token, userDetails)) {
    //             UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
    //                     null, userDetails.getAuthorities());
    //             authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    //             SecurityContextHolder.getContext().setAuthentication(authToken);
    //         }
    //     }
    //     filterChain.doFilter(request, response);
    // }

    @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    String token = null;
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
        try {
            if (token != null && !jwtService.isTokenExpired(token)) {
                String username = jwtService.extractUsername(token);
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } else {
                // Token es nulo o ha expirado
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o expirado");
                return; // Detiene la ejecución para no continuar con el filtro.
            }
        } catch (Exception e) {
            // Manejar otras excepciones, como firma incorrecta, token malformado, etc.
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error en la autenticación");
            return;
        }
    }
    filterChain.doFilter(request, response);
}

}
