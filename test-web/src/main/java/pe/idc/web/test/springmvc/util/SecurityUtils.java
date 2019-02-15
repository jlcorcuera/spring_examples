package pe.idc.web.test.springmvc.util;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static UsernamePasswordAuthenticationToken getCurrentUser() {
        return (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

    public static boolean hasRole(String rol) {
        Collection<GrantedAuthority> authorities = getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equalsIgnoreCase(rol)) {
                return true;
            }
        }
        return false;
    }

}
