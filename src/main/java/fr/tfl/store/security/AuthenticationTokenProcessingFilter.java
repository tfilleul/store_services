package  fr.tfl.store.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import fr.anses.ct.ldap.service.ILdapService;


public class AuthenticationTokenProcessingFilter extends GenericFilterBean {
	
  @Autowired	
  private UserDetailsService userDetailsService;
  
  private final ILdapService ldapService;

  public AuthenticationTokenProcessingFilter(final ILdapService ldapService) {
    this.ldapService = ldapService;
  }

  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException,
    ServletException {
	boolean tokenValid = false;
	String authTokenCookie = null;
    UserDetails userDetails = null;
    Cookie cokieStore = null;
	HttpServletRequest httpRequest = this.getAsHttpRequest(request);
    
	Cookie[] tabCookie = httpRequest.getCookies();
	if (tabCookie != null) {
		for (int i = 0; i < tabCookie.length; i++) {
			if (tabCookie[i].getName() == "store") {
				cokieStore = tabCookie[i];
				authTokenCookie = cokieStore.getValue();
			}
		}
	}
	
	
    String authToken = this.extractAuthTokenFromRequest(httpRequest);
    String userName = TokenUtils.getUserNameFromToken(authToken);

    if (userName != null) {
    	//userDetails = ldapService.ldap_s06_search_for_authentication(userName);
    	userDetails = userDetailsService.loadUserByUsername(userName);
    	if (userDetails != null) {
		   	 if (TokenUtils.validateToken(authToken, userDetails)) {
		         UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
		           userDetails.getAuthorities());
		         authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
		         SecurityContextHolder.getContext().setAuthentication(authentication);
		         tokenValid = true;		     	
		       }
    	 }
    
	    if (!tokenValid && ((HttpServletRequest)request).getRequestURI().indexOf("logoff") == -1){
			     	  HttpServletResponse httpReponse = (HttpServletResponse) response;			     
			      		httpReponse.sendError(
			   				HttpServletResponse.SC_UNAUTHORIZED,
			   				"STORE *** Unauthorized: Authentication token was either missing or invalid.");
			      		return;
	    }
    } else if (((HttpServletRequest)request).getRequestURI().indexOf("logon") == -1 && authToken != null) {
    	// le userNale est obligatoire !!!
    	HttpServletResponse httpReponse = (HttpServletResponse) response;
  		httpReponse.sendError(
				HttpServletResponse.SC_UNAUTHORIZED,
				"STORE *** Unauthorized: Authentication token was either missing or invalid.");
  		return;
    }    
    chain.doFilter(request, response);
    
  }

  private HttpServletRequest getAsHttpRequest(final ServletRequest request) {
    if (!(request instanceof HttpServletRequest)) {
      throw new RuntimeException("Expecting an HTTP request");
    }

    return (HttpServletRequest) request;
  }

  private String extractAuthTokenFromRequest(final HttpServletRequest httpRequest) {
    /* Get token from header */
    String authToken = httpRequest.getHeader("X-Auth-Token");

    /* If token not found get it from request parameter */
    if (authToken == null) {
      authToken = httpRequest.getParameter("token");
    }

    return authToken;
  }
 
  /**
   * @return the ldapService
   */
  public ILdapService getLdapService() {
    return ldapService;
  }

}