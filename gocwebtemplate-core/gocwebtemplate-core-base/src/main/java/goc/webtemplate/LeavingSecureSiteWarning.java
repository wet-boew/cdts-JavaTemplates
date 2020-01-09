package goc.webtemplate;

import java.io.Serializable;

public class LeavingSecureSiteWarning implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean enabled;
    private boolean displayModalWindow;
    private String message;
    private String redirectUrl;
    private String excludedDomains;
    private String cancelMessage;
    private String yesMessage;
    
    public LeavingSecureSiteWarning() {
    }
    
    public LeavingSecureSiteWarning(boolean enabled, boolean displayModalWindow, String message, String redirectUrl, String excludedDomains, String cancelMessage, String yesMessage) {
        this.enabled = enabled;
        this.displayModalWindow = displayModalWindow;
        this.message = message;
        this.redirectUrl = redirectUrl;
        this.excludedDomains = excludedDomains;
        this.cancelMessage = cancelMessage;
        this.yesMessage = yesMessage;
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

    /**
     * Returns the text for the button (Cancel) the users will get if they want to close the exitMsg window. 
     * @return String of text, or null if none specified
     */
    public String getCancelMessage() {
        return cancelMessage;
    }

    /**
     * Sets the text for the button (Cancel) the users will get if they want to close the exitMsg window. 
     * @param cancelMessage The button text
     */
    public void setCancelMessage(String cancelMessage) {
        this.cancelMessage = cancelMessage;
    }

    /**
     * Returns the text for the button (Yes) the users will get if they want to close the exitMsg window.
     * @return String of text, or null if none specified
     */
    public String getYesMessage() {
        return yesMessage;
    }

    /**
     * Sets the text for the button (Yes) the users will get if they want to close the exitMsg window.
     * @param yesMessage The button text
     */
    public void setYesMessage(String yesMessage) {
        this.yesMessage = yesMessage;
    }    
}
