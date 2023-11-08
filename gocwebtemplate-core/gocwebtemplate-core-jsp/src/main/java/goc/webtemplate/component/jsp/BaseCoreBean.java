package goc.webtemplate.component.jsp;

import java.net.URLEncoder;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import goc.webtemplate.Constants;
import goc.webtemplate.component.AbstractCoreBean;

public abstract class BaseCoreBean extends AbstractCoreBean {
    private HttpServletRequest request = null;  

    public void setRequest(HttpServletRequest req) {
        this.request = req;
    }
    
    public HttpServletRequest getRequest() { 
        return this.request; 
    }

    @Override
    protected String getDefaultLanguageLinkUrl() {
        try {
            String requestUri = (String)this.request.getAttribute("javax.servlet.forward.request_uri");
            if (requestUri == null) requestUri = this.request.getRequestURI();
            String currentUrlEncoded = URLEncoder.encode(requestUri + (this.request.getQueryString() == null ? "" : "?" + this.request.getQueryString()), "UTF-8");
            return this.request.getContextPath() + "/switchlocale.action?" + Constants.QUERYSTRING_KEY + "=" + currentUrlEncoded;
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    @Override
    protected String getDefaultLeaveSecureSiteRedirectUrl() {
        return null;
    }
    
    @Override
    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("goc.webtemplate.global.config.cdn");
    }
    
    @Override
    public String getResourceBundleString(String resourceBundleName, String resourceBundleKey) throws MissingResourceException {
        try {
            return (java.util.ResourceBundle.getBundle("goc.webtemplate.global.config." + resourceBundleName)).getString(resourceBundleKey);
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    @Override
    public String getTwoLetterCultureLanguage() {
        if (this.request == null) return "en";
        
        if (this.request.getSession().getAttribute(Constants.CURRENT_LANG_SESSION_KEY) == null) 
            return this.request.getLocale().getLanguage();
        else 
            return ((java.util.Locale)request.getSession().getAttribute(Constants.CURRENT_LANG_SESSION_KEY)).getLanguage();
    }
}
