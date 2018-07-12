package goc.webtemplate.component.jsf;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goc.webtemplate.component.BaseUtil;

public class SwitchLocaleFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		String uri = ((HttpServletRequest)req).getRequestURI();
		
		if (uri.indexOf("switchlocale.xhtml") != -1) {
			try {
				BaseUtil.doLocaleSwitch((HttpServletRequest)req, (HttpServletResponse)res);
			}
			catch (Exception ex) { chain.doFilter(req, res); }
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
