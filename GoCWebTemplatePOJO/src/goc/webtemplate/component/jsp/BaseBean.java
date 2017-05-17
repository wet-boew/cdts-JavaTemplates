package goc.webtemplate.component.jsp;

import java.net.URLEncoder;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import goc.webtemplate.Constants;
import goc.webtemplate.component.BaseComponent;

/**
 * @deprecated THIS CLASS WILL BE DELETED IN AN UPCOMING RELEASE, IT HAS BEEN REPLACED BY BaseCoreBean
 * @see BaseCoreBean
 */
@Deprecated
public abstract class BaseBean extends BaseComponent {
	private HttpServletRequest req = null;	

	@Override
	protected String getDefaultLanguageLinkUrl() {
		try {
			String requestUri = (String)this.req.getAttribute("javax.servlet.forward.request_uri");
			if (requestUri == null) requestUri = this.req.getRequestURI();
			String currentUrlEncoded = URLEncoder.encode(requestUri + (this.req.getQueryString() == null ? "" : "?" + this.req.getQueryString()), "UTF-8");
			return this.req.getContextPath() + "/switchlocale.action?" + Constants.QUERYSTRING_KEY + "=" + currentUrlEncoded;
		}
		catch (Exception ex) {
			return "";
		}
	}
	
	@Override
	protected String getDefaultLeaveSecureSiteRedirectUrl() {
		return "leavesecuresiteredirect.action";
	}
	
	public void setRequest(HttpServletRequest req) {
		this.req = req;
	}
	
	public HttpServletRequest getRequest() { return this.req; }
	
	public ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle("goc.webtemplate.global.config.cdn");
	}
	
	public String getResourceBundleString(String resourceBundleName, String resourceBundleKey) throws MissingResourceException {
    	return (java.util.ResourceBundle.getBundle("goc.webtemplate.global.config." + resourceBundleName)).getString(resourceBundleKey);
	}
    
    public String getTwoLetterCultureLanguage() {
    	if (this.req == null) return "en";
		
    	if (this.req.getSession().getAttribute(Constants.CURRENT_LANG_SESSION_KEY) == null) 
			return this.req.getLocale().getLanguage();
		else 
			return ((java.util.Locale)req.getSession().getAttribute(Constants.CURRENT_LANG_SESSION_KEY)).getLanguage();
    }
}
