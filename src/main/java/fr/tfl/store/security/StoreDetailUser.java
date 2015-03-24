package fr.tfl.store.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class StoreDetailUser implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String login;
    private final String password;
    private final boolean enabled;
    
	public StoreDetailUser(String login, String password, boolean enabled) {
		super();
		this.login = login;
		this.password = password;
		this.enabled = enabled;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return null;
	}

	public String getPassword() {		
		return password;
	}

	public String getUsername() {		
		return login;
	}

	public boolean isAccountNonExpired() {		
		return true;
	}

	public boolean isAccountNonLocked() {		
		return true;
	}

	public boolean isCredentialsNonExpired() {	
		return true;
	}

	public boolean isEnabled() {		
		return enabled;
	}

}
