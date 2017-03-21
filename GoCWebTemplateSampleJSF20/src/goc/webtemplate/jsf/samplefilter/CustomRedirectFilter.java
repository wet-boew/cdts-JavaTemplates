package goc.webtemplate.jsf.samplefilter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomRedirectFilter implements Filter {

    /**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		String uri = ((HttpServletRequest)req).getRequestURI();
		
		if (uri.indexOf("customredirect.xhtml") != -1) {
			try {
				HttpServletRequest currentReq = (HttpServletRequest)req;
				String redirectUrl = URLDecoder.decode(currentReq.getParameter("targetUrl"), "UTF-8");
				((HttpServletResponse)res).sendRedirect(redirectUrl);
			}
			catch (Exception ex) { chain.doFilter(req, res); }
		} else {
			chain.doFilter(req, res);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {}

}
