package pe.idc.web.test.springmvc.security;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.idc.web.test.springmvc.util.KaptchaUtils;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        boolean validKaptcha = KaptchaUtils.validate(request, null);
        String enterpriseStr = request.getParameter("enterprise");
        Integer enterpriseId = (enterpriseStr == null || enterpriseStr.isEmpty()) ? 0 : Integer.parseInt(enterpriseStr);
        LOGGER.info("user: {}", name);
        LOGGER.info("password: {}", password);
        LOGGER.info("validKaptcha: {}", validKaptcha);
        LOGGER.info("enterpriseId: {}", enterpriseId);
        if (!validKaptcha) {
            throw new InsufficientAuthenticationException("Codigo kaptcha invalido.");
        }
        // 1. obtener usuario de base de datos en base al email
        // 2. si el usuario existe entonces encriptar password recibido y compararlo con el de base de datos.
        // 3. si el usuario no existe o el password es incorrect mostrar mensaje de error
        if ("admin".equals(name) && "admin".equals(password)) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new AuthenticatedUser(1, name, enterpriseId, authorities);
        } else if ("user".equals(name) && "user".equals(password)) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new AuthenticatedUser(2, name, enterpriseId, authorities);
        } else {
            throw new BadCredentialsException("Error en credenciales");
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
