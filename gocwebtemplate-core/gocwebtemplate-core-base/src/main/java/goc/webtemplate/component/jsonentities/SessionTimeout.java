package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import goc.webtemplate.SessionTimeoutTextOverrides;
import goc.webtemplate.component.JsonValueUtils;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as the 'data-wb-sessto' attribute of a '<span class='wb-sessto'...' HTML element 
 * for WET timeout control in the template pages. 
 * 
 * NOTE: At first glance is a duplicate of goc.webtemplate.SessionTimeout,
 *       but is needed for non-standard/inconsistent casing of attribute names,
 *       nullable types and handling of empty string values for optional parameters. 
 */
public class SessionTimeout implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer inactivity;
    private Integer reactionTime;
    private Integer sessionalive;
    private String logouturl = null;
    private String refreshCallbackUrl = null;
    private Boolean refreshOnClick = false;
    private Integer refreshLimit = null;
    private String method = null;
    private String additionalData = null;
    private String signInUrl = null;
    private SessionTimeoutTextOverrides textOverrides = null;
    
    public SessionTimeout() {
    }
    
    /**
     * Convenience constructor to convert from goc.webtemplate.SessionTimeout object.
     * 
     * @see goc.webtemplate.SessionTimeout
     */
    public SessionTimeout(goc.webtemplate.SessionTimeout otherObj) {
        this.inactivity = otherObj.getInactivity();
        this.reactionTime = otherObj.getReactionTime();
        this.sessionalive = otherObj.getSessionAlive();
        this.logouturl = otherObj.getLogoutUrl();
        this.refreshCallbackUrl = JsonValueUtils.getNonEmptyString(otherObj.getRefreshCallbackUrl());        
        this.refreshOnClick = otherObj.isRefreshOnClick();
        this.refreshLimit = otherObj.getRefreshLimit() > 0? otherObj.getRefreshLimit(): null;
        this.method = JsonValueUtils.getNonEmptyString(otherObj.getMethod());
        this.additionalData = JsonValueUtils.getNonEmptyString(otherObj.getAdditionalData());
        this.signInUrl = JsonValueUtils.getNonEmptyString(otherObj.getSignInUrl());
        this.textOverrides = otherObj.getTextOverrides();
    }
    
    public SessionTimeout(Integer inactivity, Integer reactionTime, Integer sessionalive,
                          String logouturl, String refreshCallbackUrl, Boolean refreshOnClick,
                          Integer refreshLimit, String method, String additionalData, String signInUrl) {
        this.inactivity = inactivity;
        this.reactionTime = reactionTime;
        this.sessionalive = sessionalive;
        this.logouturl = logouturl;
        this.refreshCallbackUrl = refreshCallbackUrl;
        this.refreshOnClick = refreshOnClick;
        this.refreshLimit = refreshLimit;
        this.method = method;
        this.additionalData = additionalData;
        this.signInUrl = signInUrl;
        this.textOverrides = textOverrides;
    }

    public Integer getInactivity() {
        return inactivity;
    }

    public void setInactivity(Integer inactivity) {
        this.inactivity = inactivity;
    }

    public Integer getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(Integer reactionTime) {
        this.reactionTime = reactionTime;
    }

    public Integer getSessionalive() {
        return sessionalive;
    }

    public void setSessionalive(Integer sessionalive) {
        this.sessionalive = sessionalive;
    }

    public String getLogouturl() {
        return logouturl;
    }

    public void setLogouturl(String logouturl) {
        this.logouturl = logouturl;
    }

    public String getRefreshCallbackUrl() {
        return refreshCallbackUrl;
    }

    public void setRefreshCallbackUrl(String refreshCallbackUrl) {
        this.refreshCallbackUrl = refreshCallbackUrl;
    }

    public Boolean getRefreshOnClick() {
        return refreshOnClick;
    }

    public void setRefreshOnClick(Boolean refreshOnClick) {
        this.refreshOnClick = refreshOnClick;
    }

    public Integer getRefreshLimit() {
        return refreshLimit;
    }

    public void setRefreshLimit(Integer refreshLimit) {
        this.refreshLimit = refreshLimit;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }
    
    public String getSignInUrl() {
        return signInUrl;
    }

    public void setSignInUrl(String signInUrl) {
        this.signInUrl = signInUrl;
    }

    public SessionTimeoutTextOverrides getTextOverrides() {
        return textOverrides;
    }

    public void setTextOverrides(SessionTimeoutTextOverrides textOverrides) {
        this.textOverrides = textOverrides;
    }

}
