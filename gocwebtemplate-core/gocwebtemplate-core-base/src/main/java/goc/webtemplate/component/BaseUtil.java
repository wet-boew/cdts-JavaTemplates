package goc.webtemplate.component;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goc.webtemplate.Constants;

public final class BaseUtil {
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

		// Validate that the redirect link is relative to the host and NOT absolute or relative to scheme                                                
		if ((!prevUrl.startsWith("/")) || prevUrl.startsWith("//")) throw new Exception("Unauthorized return URL specified for language switching.");    

		res.sendRedirect(prevUrl);
	}
	
	public static String encodeUrl(String url) {
		return url.replace(" ", "%20").replace("\"", "%22");
	}
}
