package fr.tfl.store.filter;


import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


@WebFilter(filterName = "Filter1_CorsResponseFilter",
description = "Cross Origin Resource Sharing", urlPatterns = {"/*"},dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.ERROR})
public class CorsFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletResponse response = (HttpServletResponse) res;
		
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost:9000");
//		response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
//		response.setHeader("Access-Control-Max-Age", "3600");
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		response.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, x-auth-token, X-Auth-Token, Authorization");
//		
		chain.doFilter(req, response);
		
	}

	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}