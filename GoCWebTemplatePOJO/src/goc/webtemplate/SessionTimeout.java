package goc.webtemplate;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SessionTimeout implements Serializable {
    private boolean enabled = false;
	private int inActivity = 0;
	private int reactionTime = 0;
	private int sessionAlive = 0;
	private String logoutUrl = "";
	private String refreshCallbackUrl = "";
	private boolean refreshOnClick = false;
	private int refreshLimit = 0;
	private String method = "";
	private String additionalData = "";
    
    public SessionTimeout() {
    }

    public SessionTimeout(int inactivity, int reactiontime, int sessionalive, 
    					  String logouturl, String refreshcallbackurl, 
    					  boolean refreshonclick, int refreshlimit, 
    					  String method, String additionaldata) {

        this(false,
             inactivity, reactiontime, sessionalive,
             logouturl, refreshcallbackurl,
             refreshonclick, refreshlimit,
             method, additionaldata);
    }

    public SessionTimeout(boolean enabled, int inactivity, int reactiontime, int sessionalive, 
            String logouturl, String refreshcallbackurl, 
            boolean refreshonclick, int refreshlimit, 
            String method, String additionaldata) {

        this.enabled = enabled;
        this.inActivity = inactivity;
        this.reactionTime = reactiontime;
        this.sessionAlive = sessionalive;
        this.logoutUrl = logouturl;
        this.refreshCallbackUrl = refreshcallbackurl;
        this.refreshOnClick = refreshonclick;
        this.refreshLimit = refreshlimit;
        this.method = method;
        this.additionalData = additionaldata;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
	public int getInActivity() {
		return inActivity;
	}

	public void setInActivity(int inActivity) {
		this.inActivity = inActivity;
	}

	public int getReactionTime() {
		return reactionTime;
	}

	public void setReactionTime(int reactionTime) {
		this.reactionTime = reactionTime;
	}

	public int getSessionAlive() {
		return sessionAlive;
	}

	public void setSessionAlive(int sessionAlive) {
		this.sessionAlive = sessionAlive;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getRefreshCallbackUrl() {
		return refreshCallbackUrl;
	}

	public void setRefreshCallbackUrl(String refreshCallbackUrl) {
		this.refreshCallbackUrl = refreshCallbackUrl;
	}

	public boolean isRefreshOnClick() {
		return refreshOnClick;
	}

	public void setRefreshOnClick(boolean refreshOnClick) {
		this.refreshOnClick = refreshOnClick;
	}

	public int getRefreshLimit() {
		return refreshLimit;
	}

	public void setRefreshLimit(int refreshLimit) {
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
}
