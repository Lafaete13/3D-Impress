package br.com.impress3d.ecommerce.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.impress3d.ecommerce.persistence.AdministradorRepository;
import br.com.impress3d.ecommerce.persistence.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
class ApplicationConfig {
	
	@Autowired
    private ClienteRepository clienteRepository;
	@Autowired
    private  AdministradorRepository administradorRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Tenta encontrar como cliente primeiro
            return clienteRepository.findByEmail(username)
                .map(cliente -> new User(
                    cliente.getEmail(),
                    cliente.getSenha(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                ))
                .orElseGet(() -> 
                    // Se não encontrar, tenta como administrador
                    administradorRepository.findByEmail(username)
                        .map(admin -> new User(
                            admin.getEmail(),
                            admin.getSenha(),
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                        ))
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + username))
                );
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
