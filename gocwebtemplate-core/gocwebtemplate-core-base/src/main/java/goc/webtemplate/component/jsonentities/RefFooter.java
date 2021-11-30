package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import goc.webtemplate.LeavingSecureSiteWarning;

import goc.webtemplate.Utility;
import goc.webtemplate.WebAnalyticsInfo;

import goc.webtemplate.component.JsonValueUtils;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.refFooter' JavaScript function in the template
 * pages. 
 */
public class RefFooter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String  cdnEnv;
    private ExitSecureSite exitSecureSite;
    private String  jqueryEnv;
    private String  localPath;
    private boolean webAnalytics;  
    
    public RefFooter() {
    }

    public RefFooter(String cdnEnv, ExitSecureSite exitSecureSite, String jqueryEnv, String localPath, boolean webAnalytics) {
        this.cdnEnv = cdnEnv;
        this.exitSecureSite = exitSecureSite;
        this.jqueryEnv = jqueryEnv;
        this.localPath = localPath;
        this.webAnalytics = webAnalytics;
    }
    
    public RefFooter(String cdnEnv, LeavingSecureSiteWarning lssw, String jqueryEnv, String localPath, WebAnalyticsInfo webAnalyticsInfo) {
        this.cdnEnv = cdnEnv;
        this.exitSecureSite = null;
        if ((lssw != null) && lssw.isEnabled() && !Utility.isNullOrEmpty(lssw.getRedirectUrl())) {
            this.exitSecureSite = new ExitSecureSite(lssw);
        }
        this.jqueryEnv = jqueryEnv;
        this.localPath = localPath;
        this.webAnalytics = (webAnalyticsInfo != null && webAnalyticsInfo.isActive());
    }

    public String getCdnEnv() {
        return cdnEnv;
    }

    public void setCdnEnv(String cdnEnv) {
        this.cdnEnv = cdnEnv;
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

    public boolean isWebAnalytics() {
        return webAnalytics;
    }

    public void setWebAnalytics(boolean webAnalytics) {
        this.webAnalytics = webAnalytics;
    }

    public ExitSecureSite getExitSecureSite() {
        return exitSecureSite;
    }

    public void setExitSecureSite(ExitSecureSite exitSecureSite) {
        this.exitSecureSite = exitSecureSite;
    }
}
