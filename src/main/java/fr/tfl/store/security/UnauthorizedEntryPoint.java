package fr.tfl.store.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


/**
 * 
 * @author aszalajs
 *
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint
{
	  /** Logger **/
	  private static final Logger logger = LoggerFactory.getLogger(UnauthorizedEntryPoint.class);

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException
	{
		String remoteHost = request.getRemoteAddr();
		String uri = request.getRequestURI();
		logger.error("Tentative d'accès à la resource " + uri  +  " sans authentification depuis " + remoteHost);
		response.sendError(
				HttpServletResponse.SC_UNAUTHORIZED,
				"ANSES *** Unauthorized: Authentication token was either missing or invalid.");
	}

}