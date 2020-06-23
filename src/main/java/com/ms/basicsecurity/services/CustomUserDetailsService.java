package com.ms.basicsecurity.services;

import com.ms.basicsecurity.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(u-> new User(u.getUsername(),u.getPassword(),u.isActive(),u.isActive(),u.isActive(),u.isActive(),
                        AuthorityUtils.createAuthorityList("ADMIN","USER")))
                .orElseThrow(()->new UsernameNotFoundException("Could not find user with username : "+username));
    }
}
