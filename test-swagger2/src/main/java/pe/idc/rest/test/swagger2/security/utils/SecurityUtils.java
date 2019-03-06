package pe.idc.rest.test.swagger2.security.utils;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import pe.idc.rest.test.swagger2.security.model.AuthenticatedUser;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static AuthenticatedUser getCurrentUser() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authenticationToken.getPrincipal();
        return authenticatedUser;
    }

    public static boolean hasRole(String rol) {
        Collection<? extends GrantedAuthority> authorities = getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equalsIgnoreCase(rol)) {
                return true;
            }
        }
        return false;
    }

}
