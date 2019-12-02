package com.lirugo.print_service.service;

import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.enums.UserRole;
import com.lirugo.print_service.repo.UserRepo;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

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
        GoogleOAuth2UserService googleOAuth2User = new GoogleOAuth2UserService(oidcUser.getAttributes());

        Optional<User> userOptional = Optional.ofNullable(userRepo.findByEmail(googleOAuth2User.getEmail()));

        //This is new user, store to db
        if (!userOptional.isPresent()) {
            User user = new User();
            user.setId(googleOAuth2User.getId());
            user.setEmail(googleOAuth2User.getEmail());
            user.setName(googleOAuth2User.getName());
            user.setPicture(googleOAuth2User.getPicture());
            user.setRoles(Collections.singleton(UserRole.USER));

            userRepo.save(user);
        }

        return oidcUser;
    }
}
