package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.List;

import goc.webtemplate.LeavingSecureSiteWarning;
import goc.webtemplate.WebAnalyticsInfo;

/**
 * Objects of this class are meant to be serialized to a JSON object to be
 * passed as part of "base" parameter to the 'wet.builder.setup' JavaScript
 * function in the template pages. (see Setup class)
 */
public class SetupBase implements Serializable {
    private static final long serialVersionUID = 1L;

    private String subTheme;
    private String jqueryEnv;
    private ExitSecureSite exitSecureSite;
    private List<WebAnalyticsInfo> webAnalytics;
    private Boolean sriEnabled;

    public SetupBase() {
    }

    public SetupBase(String subTheme, String jqueryEnv, ExitSecureSite exitSecureSite,
            List<WebAnalyticsInfo> webAnalytics, Boolean sriEnabled) {
        this.subTheme = subTheme;
        this.jqueryEnv = jqueryEnv;
        this.exitSecureSite = exitSecureSite;
        this.webAnalytics = webAnalytics;
        this.sriEnabled = sriEnabled;
    }

    public SetupBase(String subTheme, String jqueryEnv, LeavingSecureSiteWarning lssw,
            List<WebAnalyticsInfo> webAnalytics, Boolean sriEnabled) {
        this.subTheme = subTheme;
        this.jqueryEnv = jqueryEnv;
        this.exitSecureSite = null;
        if ((lssw != null) && lssw.isEnabled()) {
            this.exitSecureSite = new ExitSecureSite(lssw);
        }
        this.webAnalytics = webAnalytics;
        this.sriEnabled = sriEnabled;
    }

    public String getSubTheme() {
        return subTheme;
    }

    public void setSubTheme(String subTheme) {
        this.subTheme = subTheme;
    }

    public String getJqueryEnv() {
        return this.jqueryEnv;
    }

    public void setJqueryEnv(String value) {
        this.jqueryEnv = value;
    }

    public ExitSecureSite getExitSecureSite() {
        return this.exitSecureSite;
    }

    public void setExitSecureSite(ExitSecureSite value) {
        this.exitSecureSite = value;
    }

    public List<WebAnalyticsInfo> getWebAnalytics() {
        return webAnalytics;
    }

    public void setWebAnalytics(List<WebAnalyticsInfo> webAnalytics) {
        this.webAnalytics = webAnalytics;
    }

    public Boolean isSriEnabled() {
        return sriEnabled;
    }

    public void setSriEnabled(Boolean sriEnabled) {
        this.sriEnabled = sriEnabled;
    }
}
