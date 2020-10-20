package com.itqpleyva.springjwtsecurity.Services;

import java.util.ArrayList;

import com.google.common.base.Optional;
import com.itqpleyva.springjwtsecurity.Models.Usuario;
import com.itqpleyva.springjwtsecurity.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
      
        Usuario user = userRepository.findByusername(userName);
        MyUserDetails userDetails = new MyUserDetails(user);
        System.out.println("Role:");
        System.out.println(userDetails.getAuthorities());
        
        return userDetails;

    }

} 