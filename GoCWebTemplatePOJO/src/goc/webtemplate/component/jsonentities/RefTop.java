package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.refTop' JavaScript function in the template
 * pages. 
 */
public class RefTop implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cdnEnv;
    private String subTheme;
    private String jqueryEnv;
    private String localPath;
    
    public RefTop() {
    }
    
    public RefTop(String cdnEnv, String subTheme, String jqueryEnv, String localPath) {
        this.cdnEnv = cdnEnv;
        this.subTheme = subTheme;
        this.jqueryEnv = jqueryEnv;
        this.localPath = localPath;
    }

    public String getCdnEnv() {
        return cdnEnv;
    }

    public void setCdnEnv(String cdnEnv) {
        this.cdnEnv = cdnEnv;
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
    
    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }    
}
