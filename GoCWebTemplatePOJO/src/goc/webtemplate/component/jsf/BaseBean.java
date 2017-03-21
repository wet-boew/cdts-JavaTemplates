package goc.webtemplate.component.jsf;

import java.net.URLEncoder;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import goc.webtemplate.Constants;
import goc.webtemplate.component.BaseComponent;

public abstract class BaseBean extends BaseComponent {
	@Override
	protected String getDefaultLangLinkUrl() {
		try {
			HttpServletRequest currentReq = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String currentUrlEncoded = URLEncoder.encode(currentReq.getRequestURI() + (currentReq.getQueryString() == null ? "" : "?" + currentReq.getQueryString()), "UTF-8");
			return currentReq.getContextPath() + "/faces/templates/switchlocale.xhtml?" + Constants.QUERYSTRING_KEY + "=" + currentUrlEncoded;
		}
		catch (Exception ex) {
			return "";
		}
	}
	
	@Override
	protected String getDefaultLeaveSecureSiteRedirectUrl() {
		return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath() + "/faces/templates/leavesecuresiteredirect.xhtml";
	}
	
    public ResourceBundle getResourceBundle() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "cdn");
        return bundle;
	}
	
    public String getResourceBundleString(String resourceBundleName, String resourceBundleKey) throws MissingResourceException {
    	try {
    		FacesContext facesContext = FacesContext.getCurrentInstance();
            ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, resourceBundleName);
            return bundle.getString(resourceBundleKey);
    	}
    	catch (Exception ex) {
    		return "";
    	}
	}
    
    public String getTwoLetterCultureLanguage() {
		FacesContext facesCtx = FacesContext.getCurrentInstance();
		ExternalContext extCtx = facesCtx.getExternalContext();
		HttpServletRequest req = (HttpServletRequest)extCtx.getRequest();
		
		if (req.getSession().getAttribute(Constants.CURRENT_LANG_SESSION_KEY) == null) 
			return req.getLocale().getLanguage();
		else 
			return ((java.util.Locale)req.getSession().getAttribute(Constants.CURRENT_LANG_SESSION_KEY)).getLanguage();
		
    }
}
