package goc.webtemplate;

import java.io.Serializable;

public class LeavingSecureSiteWarning implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean enabled;
    private boolean displayModalWindow;
    private String message;
    private String redirectUrl;
    private String excludedDomains;
    
    public LeavingSecureSiteWarning() {
    }
    
    public LeavingSecureSiteWarning(boolean enabled, boolean displayModalWindow, String message, String redirectUrl, String excludedDomains) {
        this.enabled = enabled;
        this.displayModalWindow = displayModalWindow;
        this.message = message;
        this.redirectUrl = redirectUrl;
        this.excludedDomains = excludedDomains;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getDisplayModalWindow() {
        return displayModalWindow;
    }

    public void setDisplayModalWindow(boolean displayModalWindow) {
        this.displayModalWindow = displayModalWindow;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getExcludedDomains() {
        return excludedDomains;
    }

    public void setExcludedDomains(String excludedDomains) {
        this.excludedDomains = excludedDomains;
    }
}
