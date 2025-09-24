package br.com.fiap.sprint3.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.sprint3.model.entity.Usuario;
import br.com.fiap.sprint3.model.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails usuario = usuarioRepository.findByEmail(username)
	            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: "));
		return usuario;
	}

    
}

