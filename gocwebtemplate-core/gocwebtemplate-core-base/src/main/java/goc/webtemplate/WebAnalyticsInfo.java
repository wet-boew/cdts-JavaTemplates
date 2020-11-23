package goc.webtemplate;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Represents information on the inclusion of web usage analysis within 
 * web pages.
 * 
 * @since 1.0.32
 */
public class WebAnalyticsInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
 
    public static enum EnvironmentOption {
        @SerializedName("production") PRODUCTION, 
        @SerializedName("staging") STAGING
    }
    
    
    private transient boolean active; //Gson will ignore transient fields, equivalent to @Expose(serialize = false, deserialize = false) 
    private EnvironmentOption environment;
    private int version;
    private String custom;
    
    
    public WebAnalyticsInfo() {
        this(false, EnvironmentOption.PRODUCTION, 2);
    }
    
    public WebAnalyticsInfo(boolean active) {
        this(active, EnvironmentOption.PRODUCTION, 2);
    }
    
    public WebAnalyticsInfo(boolean active, EnvironmentOption environment, int version) {
        this.active = active;
        this.environment = environment;
        this.version = version;
        this.custom = null;
    }
    
    public WebAnalyticsInfo(boolean active, String custom) {
        this.active = active;
        this.custom = custom;
    }

    
    /**
     * Returns whether or not analytics should be used
     * @return true or false, whether analytics are enabled.
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Sets whether or not analytics should be used
     * @param value true or false, whether analytics are enabled.
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * Returns the select AA environment.
     *  
     * @return Environment
     */
    public EnvironmentOption getEnvironment() {
        return environment;
    }

    /**
     * Sets the running AA environment.
     * 
     * @param environment The selected environment.
     */
    public void setEnvironment(EnvironmentOption environment) {
        this.environment = environment;
    }

    /**
     * Returns the version of AA that will be used.
     * @return A number indicating the AA version.
     */
    public int getVersion() {
        return this.version;
    }
    
    /**
     * Sets the version of AA used by the application/site.  
     * 
     * @param version Currently only version 1 and 2 are supported.
     */
    public void setVersion(int version) {
        this.version = version;
    }
    
    /**
     * Returns the custom script that will be used for version 3 of analytics.
     * @return A string indicating the analytics script.
     */
    public String getCustom() {
        return this.custom;
    }
    
    /**
     * Sets the script used for analytics.  
     * 
     * @param custom Currently used for version 3 of analytics.
     */
    public void setCustom(String custom) {
        this.custom = custom;
    }
}
