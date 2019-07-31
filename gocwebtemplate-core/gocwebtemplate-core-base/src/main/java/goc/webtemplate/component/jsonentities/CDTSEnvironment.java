package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * Holds information/configuration related to a CDTS environment
 * (mostly for the CDTSEnvironment.json file). 
 */
public class CDTSEnvironment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String  name;
    
    private String  path;
    private String  localPath;
    
    private String  theme;
    private String  subTheme;
    private String  cdn; 
    
    private boolean isVersionRnCombined;
    private boolean isEncryptionModifiable;
    private String  appendToTitle;
    
    private int 	footerSectionLimit;
    private boolean	canHaveMultipleContactLinks;
    private boolean	canHaveContactLinkInAppTemplate;
    
    public CDTSEnvironment() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getSubTheme() {
        return subTheme;
    }

    public void setSubTheme(String subTheme) {
        this.subTheme = subTheme;
    }

    public String getCDN() {
        return this.cdn;
    }
    
    public void setCDN(String value) {
        this.cdn = value;
    }
    
    public boolean isVersionRnCombined() {
        return isVersionRnCombined;
    }

    public void setVersionRNCombined(boolean isVersionRnCombined) {
        this.isVersionRnCombined = isVersionRnCombined;
    }

    public boolean isEncryptionModifiable() {
        return isEncryptionModifiable;
    }

    public void setEncryptionModifiable(boolean value) {
        this.isEncryptionModifiable = value;
    }

    public String getAppendToTitle() {
        return this.appendToTitle;
    }
    
    public void setAppendToTitle(String value) {
        this.appendToTitle = value;
    }

    public int getFooterSectionLimit() {
        return footerSectionLimit;
    }

    public void setFooterSectionLimit(int footerSectionLimit) {
        this.footerSectionLimit = footerSectionLimit;
    }

    public boolean getCanHaveMultipleContactLinks() {
        return canHaveMultipleContactLinks;
    }

    public void setCanHaveMultipleContactLinks(boolean canHaveMultiContactLinks) {
        this.canHaveMultipleContactLinks = canHaveMultiContactLinks;
    }

    public boolean getCanHaveContactLinkInAppTemplate() {
        return canHaveContactLinkInAppTemplate;
    }

    public void setCanHaveContactLinkInAppTemplate(boolean canHaveContactLinkInAppTemplate) {
        this.canHaveContactLinkInAppTemplate = canHaveContactLinkInAppTemplate;
    }

	/**
     * Exists as a container of CDTSEnvironment matching the format of the
     * JSON file the data is read from.
     */
    public static class CDTSEnvironmentList {
        private ArrayList<CDTSEnvironment>  environments;
        
        public CDTSEnvironmentList() {
        }
        
        public ArrayList<CDTSEnvironment> getEnvironments() {
            return this.environments;
        }
        
        public void setEnvironments(ArrayList<CDTSEnvironment> value) {
            this.environments = value;
        }
    }
}
