package com.lirugo.print_service.service.auth;

import com.lirugo.print_service.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Set;

@Data
public class UserPrincipal extends DefaultOidcUser {

    private User user;

    public UserPrincipal(Set<GrantedAuthority> authorities, OidcIdToken idToken, OidcUserInfo userInfo, User user) {
        super(authorities, idToken, userInfo);

        this.user = user;
    }
}
