package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to CDTS JavaScript functions that only need "cdnEnv"
 * (like serverTop, serverBottom, serverRefTop, serverRefBottom)
 * 
 * (Corresponds to the "CDNEnvOnly" class in the .NET project)
 */
public class CdnEnvironment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String  cdnEnv;

    
    public CdnEnvironment() {
    }
    
    public CdnEnvironment(String cdnEnv) {
        this.cdnEnv = cdnEnv;
    }
    
    public String getCdnEnv() {
        return cdnEnv;
    }

    public void setCdnEnv(String cdnEnv) {
        this.cdnEnv = cdnEnv;
    }
}
