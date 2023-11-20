package ru.umom.umombackend.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

// класс конвертер из данных JWT в роли spring security
@Component
public class KCRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {



    private final String CLIENT_ID;

    public KCRoleConverter(@Value("${keycloak.client.business}") String clientId) {
        this.CLIENT_ID = clientId;
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        Map<String, Object> clientAccess = ((Map<String, Object>) ((Map<String, Object>) jwt.getClaims().get("resource_access")).get(CLIENT_ID));

        if (clientAccess == null || clientAccess.isEmpty()) {
            return new ArrayList<>();
        }

        Collection<GrantedAuthority> returnValue = new ArrayList<>();


        for (String roleName : (List<String>) clientAccess.get("roles")) {
            returnValue.add(new SimpleGrantedAuthority("ROLE_" + roleName));
        }

        return returnValue;
    }

}