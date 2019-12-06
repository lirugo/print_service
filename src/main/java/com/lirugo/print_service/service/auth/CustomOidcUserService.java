package com.lirugo.print_service.service.auth;

import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.enums.UserRole;
import com.lirugo.print_service.repo.UserRepo;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomOidcUserService extends OidcUserService {

    private final UserRepo userRepo;

    public CustomOidcUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
            return processOidcUser(oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OidcUser processOidcUser(OidcUser oidcUser) {
        User user = userRepo.findByGoogleId(oidcUser.getAttributes().get("sub").toString());

        //This is new user, store to db
        if (user == null) {
            user = new User();
            user.setGoogleId(oidcUser.getAttributes().get("sub").toString());
            user.setEmail(oidcUser.getAttributes().get("email").toString());
            user.setName(oidcUser.getAttributes().get("name").toString());
            user.setPicture(oidcUser.getAttributes().get("picture").toString());
            user.setRoles(Collections.singleton(UserRole.USER));

            user = userRepo.save(user);
        }

        Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
        mappedAuthorities.add(new SimpleGrantedAuthority(UserRole.USER.toString()));

        return new UserPrincipal(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo(), user);
    }
}
