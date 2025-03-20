package fr.hovedopgave.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Component;

import fr.hovedopgave.demo.model.UserEntity;
import fr.hovedopgave.demo.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthenticationHelper {
    private final JWTGenerator generator;
    private final UserRepository userRepository;

    public AuthenticationHelper(JWTGenerator generator, UserRepository userRepository) {
        this.generator = generator;
        this.userRepository = userRepository;
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null 
               && authentication.isAuthenticated() 
               && !(authentication instanceof AnonymousAuthenticationToken);
    }

    public UserEntity getUser(HttpServletRequest request) {
        String jwtToken = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    jwtToken = cookie.getValue();
                    break;
                }
            }
        }

        if (jwtToken != null) {
            return userRepository.findByUsername(generator.getUsernameFromJWT(jwtToken)).orElse(null);
        }
        return null; 
    }

}
