package com.example.gymdemo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.gymdemo.model.Perfil;
import com.example.gymdemo.model.Usuario;
import com.example.gymdemo.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Usuario userEntity = usuarioRepository.findByusername(username)
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "El usuario: " + username + " no existe"));

                Hibernate.initialize(userEntity.getPerfil());

                List<Perfil> perfiles = Arrays.asList(userEntity.getPerfil());

                Collection<? extends GrantedAuthority> authorities = perfiles.stream()
                                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getName()))
                                .collect(Collectors.toList());
                return new User(userEntity.getUsername(), userEntity.getPassword(),
                                true,
                                true,
                                true,
                                true,
                                authorities);
        }

}
