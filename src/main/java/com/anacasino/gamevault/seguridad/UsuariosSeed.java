package com.anacasino.gamevault.seguridad;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuariosSeed implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${gamevault.admin.password}")
    private String adminPassword;

    @Override
    public void run(ApplicationArguments args) {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario a = new Usuario();
            a.setUsername("admin");
            a.setPassword(passwordEncoder.encode(adminPassword));
            a.setRol(RolUsuario.ADMIN);
            usuarioRepository.save(a);
        }
    }
}