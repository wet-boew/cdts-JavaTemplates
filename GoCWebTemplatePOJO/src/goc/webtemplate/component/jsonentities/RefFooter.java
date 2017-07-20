package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import goc.webtemplate.LeavingSecureSiteWarning;

import goc.webtemplate.Utility;
import goc.webtemplate.component.JsonValueUtils;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.refFooter' JavaScript function in the template
 * pages. 
 */
public class RefFooter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String  cdnEnv;
    private boolean exitScript;
    private String  exitURL;
    private String  exitMsg;
    private String  exitDomains;
    private boolean displayModal;
    private String  jqueryEnv;
    private String  localPath;
    
    public RefFooter() {
    }

    public RefFooter(String cdnEnv, boolean exitScript, String exitURL, String exitMsg, String exitDomains,
            boolean displayModal, String jqueryEnv, String localPath) {
        this.cdnEnv = cdnEnv;
        this.exitScript = exitScript;
        this.exitURL = exitURL;
        this.exitMsg = exitMsg;
        this.exitDomains = exitDomains;
        this.displayModal = displayModal;
        this.jqueryEnv = jqueryEnv;
        this.localPath = localPath;
    }
    
    /**
     * Convenience constructor allowing setup directly from LeavingSecureSiteWarning
     * object.  
     */
    public RefFooter(String cdnEnv, LeavingSecureSiteWarning lssw, 
            String jqueryEnv, String localPath) {
        this.cdnEnv = cdnEnv;
        
        this.exitScript = false;
        if ((lssw != null) && lssw.isEnabled() && !Utility.isNullOrEmpty(lssw.getRedirectUrl())) {
            this.exitScript = true;
            this.exitURL = lssw.getRedirectUrl();
            this.exitMsg = lssw.getMessage();
            this.exitDomains = JsonValueUtils.GetNonEmptyURLEscapedString(lssw.getExcludedDomains());
            this.displayModal = lssw.getDisplayModalWindow();
        }
        
        this.jqueryEnv = jqueryEnv;
        this.localPath = localPath;
    }

    public String getCdnEnv() {
        return cdnEnv;
    }

    public void setCdnEnv(String cdnEnv) {
        this.cdnEnv = cdnEnv;
    }

    public boolean isExitScript() {
        return exitScript;
    }

    public void setExitScript(boolean exitScript) {
        this.exitScript = exitScript;
    }

    public String getExitURL() {
        return exitURL;
    }

    public void setExitURL(String exitURL) {
        this.exitURL = exitURL;
    }

    public String getExitMsg() {
        return exitMsg;
    }

    public void setExitMsg(String exitMsg) {
        this.exitMsg = exitMsg;
    }

    public String getExitDomains() {
        return exitDomains;
    }

    public void setExitDomains(String exitDomains) {
        this.exitDomains = exitDomains;
    }

    public boolean isDisplayModal() {
        return displayModal;
    }

    public void setDisplayModal(boolean displayModal) {
        this.displayModal = displayModal;
    }

    public String getJqueryEnv() {
        return jqueryEnv;
    }

    public void setJqueryEnv(String jqueryEnv) {
        this.jqueryEnv = jqueryEnv;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
