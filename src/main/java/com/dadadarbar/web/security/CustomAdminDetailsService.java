package com.dadadarbar.web.security;

import com.dadadarbar.web.entity.Admin;
import com.dadadarbar.web.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomAdminDetailsService implements UserDetailsService{

    private final AdminRepository repository;


    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = repository.findByEmail(username).orElseThrow(()->
                new UsernameNotFoundException("Admin not found"));

        return new User(
                String.valueOf(admin.getEmail()),
                admin.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
    }
}
