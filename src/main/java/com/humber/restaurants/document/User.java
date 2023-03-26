package com.humber.restaurants.document;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.NonNull;

import lombok.Data;

@Document
@Data
public class User implements UserDetails {
    @Id
    String id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @Indexed(unique = true)
    @NonNull
    private String email;
    @JsonIgnore
    @NonNull
    private String password;
    @NonNull
    private String[] userRoles; //notes While adding roles it should be prefixed with "ROLE_"
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	
//    	return AuthorityUtils.createAuthorityList("ADMIN");
    	return  AuthorityUtils.createAuthorityList(
    			userRoles);
    	
//    	return  AuthorityUtils.createAuthorityList(
//    			userRoles.stream().map(
//    					role -> new SimpleGrantedAuthority(role)
//    					)
//    			.collect(Collectors.toList())
//    							
//    							
//    					
//    					);
//    	List<GrantedAuthority> authorities = user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
//				.collect(Collectors.toList());
//        return Collections.EMPTY_LIST;
        
//        user.getAuthorities()
//        .forEach(role -> {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()
//               .getName()));
//        });
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
