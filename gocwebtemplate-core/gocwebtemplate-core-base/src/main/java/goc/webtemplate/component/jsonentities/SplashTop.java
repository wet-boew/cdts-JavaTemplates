package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.splashTop' JavaScript function in the template
 * pages. 
 */
public class SplashTop implements Serializable {
    private static final long serialVersionUID = 1L;

    private String  cdnEnv;
    private String  localPath;
 
    public SplashTop() {
    }
    
    public SplashTop(String cdnEnv, String localPath) {
        this.cdnEnv = cdnEnv;
        this.localPath = localPath;
    }

    public String getCdnEnv() {
        return cdnEnv;
    }

    public void setCdnEnv(String cdnEnv) {
        this.cdnEnv = cdnEnv;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
