package goc.webtemplate.component;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goc.webtemplate.Constants;

public final class BaseUtil {
	public static void doLeaveSecureSite(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String redirectUrl = URLDecoder.decode(req.getParameter("targetUrl"), "UTF-8");
		res.sendRedirect(redirectUrl);
	}
	
	public static void doLocaleSwitch(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String currLang = req.getSession().getAttribute(Constants.CURRENT_LANG_SESSION_KEY) == null ? 
									req.getLocale().getLanguage() : 
									((java.util.Locale)req.getSession().getAttribute(Constants.CURRENT_LANG_SESSION_KEY)).getLanguage();
		if (currLang.equals(Constants.ENGLISH_ACCRONYM)) {
			req.getSession().setAttribute(Constants.CURRENT_LANG_SESSION_KEY, java.util.Locale.FRENCH);
		} else {
			req.getSession().setAttribute(Constants.CURRENT_LANG_SESSION_KEY, java.util.Locale.ENGLISH);
		}
		
		String prevUrl = URLDecoder.decode(req.getParameter(Constants.QUERYSTRING_KEY), "UTF-8");
		res.sendRedirect(prevUrl);
	}
	
	public static String encodeUrl(String url) {
		return url.replace(" ", "%20").replace("\"", "%22");
	}
}
