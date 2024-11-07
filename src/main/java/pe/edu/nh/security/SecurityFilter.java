package pe.edu.nh.security;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class SecurityFilter implements Filter {
	
	private static final String LOGIN_PAGE = "/login.zul";
	private static final String[] PUBLIC_PATHS = {
		LOGIN_PAGE,
		"/resources/",
		"/zkau/",
		"/images/"
	};
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String requestPath = req.getRequestURI().substring(req.getContextPath().length());
		
				
		//Verificar si es una ruta publica
		if(isPublicPath(requestPath)) {
			chain.doFilter(request, response);
			return;
		}
		
		//Verificar si el usaurio esta autenticado
		if(session == null || session.getAttribute("usuario") ==  null) {
			res.sendRedirect(req.getContextPath() + LOGIN_PAGE);
			return;
		}
		
		chain.doFilter(request, response);
		
	}
	
	private boolean isPublicPath(String path) {
		for(String publicPath : PUBLIC_PATHS) {
			if(path.startsWith(publicPath)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
