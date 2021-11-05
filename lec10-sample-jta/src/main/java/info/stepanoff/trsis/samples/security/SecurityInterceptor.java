/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.security;

import info.stepanoff.trsis.samples.rest.ForbiddenException;
//import info.stepanoff.trsis.samples.service.MyLogin;
import org.springframework.security.core.userdetails.User;
import java.lang.reflect.Method;
import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Pavel
 */
@Aspect
@Slf4j
public class SecurityInterceptor {

    @Before("@annotation(NeedRole)")
    public void invoke(JoinPoint joinPoint) {
        log.info("SecurityInterceptor: Method Name=" + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        Principal principal = (Principal) (args[args.length - 1]);
        if (principal == null) {
            throw new ForbiddenException();
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        NeedRole needRoleAnnotation = method.getAnnotation(NeedRole.class);

        String roleRegex = needRoleAnnotation.roleRegex();
        log.info("Demand roleRegex = " + roleRegex);
        //MyLogin activeUser = (MyLogin) ((Authentication) principal).getPrincipal();        
        User activeUser = (User) ((Authentication) principal).getPrincipal();
        
        for (GrantedAuthority authority:activeUser.getAuthorities())
            if (authority.getAuthority().equals(roleRegex)) //TODO: should be regex, not equals
            {
                log.info("found corect role "+roleRegex);
                return;
            } else log.info("role does not match: "+authority.getAuthority());

        log.error("Operation Unauthorized for user "+activeUser.getUsername());
        throw new ForbiddenException();

    }
}
