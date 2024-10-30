package com.example.my_quiz_backend.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
public class User implements UserDetails {

	private static final long serialVersionUID = 8623217091295761897L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private String email;
	
	@Column
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
	    name = "user_role",
	    joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"),
	    inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id")
	)
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user"})
	private Set<Result> results = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user"})
	private Set<UserResponse> responses = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
	                .collect(Collectors.toList());
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
