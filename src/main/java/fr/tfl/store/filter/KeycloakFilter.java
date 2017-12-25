package fr.tfl.store.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Component;

@Component
public class KeycloakFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		AccessToken token = null;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest httpRequest = (HttpServletRequest)req;
		KeycloakPrincipal<RefreshableKeycloakSecurityContext> kc =   (KeycloakPrincipal<RefreshableKeycloakSecurityContext>)httpRequest.getUserPrincipal();
		if (kc != null) {
			token = kc.getKeycloakSecurityContext().getToken();
		}		
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}