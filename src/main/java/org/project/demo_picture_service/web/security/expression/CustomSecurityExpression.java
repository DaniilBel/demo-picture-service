package org.project.demo_picture_service.web.security.expression;

import lombok.RequiredArgsConstructor;
import org.project.demo_picture_service.domain.user.Role;
import org.project.demo_picture_service.service.UserService;
import org.project.demo_picture_service.web.security.JwtEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("customSecurityExpression")
@RequiredArgsConstructor
public class CustomSecurityExpression {

    private final UserService userService;

    public boolean canAccessUser(
            final Long id
    ) {
//        Authentication authentication = SecurityContextHolder.getContext()
//                .getAuthentication();

        JwtEntity user = getPrincipal();
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

    public boolean canAccessPicture(
            Long pictureId
    ) {
//        Authentication authentication = SecurityContextHolder.getContext()
//                .getAuthentication();

        JwtEntity user = getPrincipal();
        Long id = user.getId();

        return userService.isPictureOwner(id, pictureId);
    }

    private JwtEntity getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return (JwtEntity) authentication.getPrincipal();
    }
}
