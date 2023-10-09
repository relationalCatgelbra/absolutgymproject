package com.example.gymdemo.service;

import java.util.Collection;
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

                Hibernate.initialize(userEntity.getUsuarioPerfiles());

                Collection<? extends GrantedAuthority> authorities = userEntity.getUsuarioPerfiles()
                                .stream()
                                .map(usuarioPerfiles -> usuarioPerfiles.getPerfil())
                                .map(perfil -> new SimpleGrantedAuthority("ROLE_" + perfil.getName()))
                                .collect(Collectors.toList());
                return new User(userEntity.getUsername(), userEntity.getPassword(),
                                true,
                                true,
                                true,
                                true,
                                authorities);
        }

}
