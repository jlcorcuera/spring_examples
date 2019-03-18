package pe.idc.web.test.springmvc.util;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import pe.idc.web.test.springmvc.security.AuthenticatedUser;

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
