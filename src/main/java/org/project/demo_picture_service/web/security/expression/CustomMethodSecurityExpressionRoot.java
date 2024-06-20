package org.project.demo_picture_service.web.security.expression;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.project.demo_picture_service.domain.user.Role;
import org.project.demo_picture_service.service.UserService;
import org.project.demo_picture_service.web.security.JwtEntity;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
@Setter
public class CustomMethodSecurityExpressionRoot
        extends SecurityExpressionRoot
        implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;
    private HttpServletRequest request;

    private UserService userService;

    public CustomMethodSecurityExpressionRoot(
            final Authentication authentication
    ) {
        super(authentication);
    }

    public boolean canAccessUser(
            final Long id
    ) {
//        Authentication authentication = SecurityContextHolder.getContext()
//                .getAuthentication();

        JwtEntity user = (JwtEntity) this.getPrincipal();
        Long userId = user.getId();

        return userId.equals(id) || hasAnyRole(Role.ROLE_ADMIN);
    }

    private boolean hasAnyRole(
//            final Authentication authentication,
            final Role... roles
    ) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        for (Role role : roles) {
            SimpleGrantedAuthority authority
                    = new SimpleGrantedAuthority(role.name());
            if (authentication.getAuthorities().contains(authority)) {
                return true;
            }
        }
        return false;
    }

    public boolean canAccessPicture(Long pictureId) {
//        Authentication authentication = SecurityContextHolder.getContext()
//                .getAuthentication();

        JwtEntity user = (JwtEntity) this.getPrincipal();
        Long id = user.getId();

        return userService.isPictureOwner(id, pictureId);
    }

    @Override
    public Object getThis() {
        return target;
    }
}
