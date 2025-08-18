package com.aimup.security;

import com.aimup.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Ajuste conforme sua autenticação.
 * Ideal: retornar a ENTIDADE Usuario logada.
 */
public final class SecurityUtil {
    private SecurityUtil() {}

    public static Usuario getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return null;

        Object principal = auth.getPrincipal();
        if (principal instanceof Usuario) {
            return (Usuario) principal;
        }

        // Se você usa UserDetails, busque o Usuario no banco a partir do username/email.
        return null;
    }
}
