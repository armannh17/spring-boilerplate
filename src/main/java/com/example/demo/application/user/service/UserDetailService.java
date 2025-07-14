package com.example.demo.application.user.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.application.user.model.UserModel;
import com.example.demo.application.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UUID id = UUID.fromString(userId);

		UserModel user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found"));

		String role = "ROLE_" + user.getRole().toString();

		return User.withUsername(user.getId().toString()).password("N/A").authorities(role).accountExpired(false)
				.accountLocked(user.getLocked()).credentialsExpired(false).disabled(false).build();
	}
}
