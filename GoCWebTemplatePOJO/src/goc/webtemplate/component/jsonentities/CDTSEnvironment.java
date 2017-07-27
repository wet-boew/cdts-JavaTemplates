package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * Holds information/configuration related to a CDTS environment
 * (mostly for the CDTSEnvironment.json file). 
 */
public class CDTSEnvironment implements Serializable {
    private static final long serialVersionUID = 1L;

    //NOTE: Name of instance variable will have a capital first letter
    //      to match the format of the JSON file.
    private String  Name;
    
    private String  Path;
    private String  LocalPath;
    
    private String  Theme;
    private String  SubTheme;
    private String  CDN; 
    
    private boolean IsVersionRnCombined;
    private boolean IsSSLModifiable;
    private String  AppendToTitle;
    
    
    public CDTSEnvironment() {
    }
    
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        this.Path = path;
    }

    public String getLocalPath() {
        return LocalPath;
    }

    public void setLocalPath(String localPath) {
        this.LocalPath = localPath;
    }

    public String getTheme() {
        return Theme;
    }

    public void setTheme(String theme) {
        this.Theme = theme;
    }

    public String getSubTheme() {
        return SubTheme;
    }

    public void setSubTheme(String subTheme) {
        this.SubTheme = subTheme;
    }

    public String getCDN() {
        return this.CDN;
    }
    
    public void setCDN(String value) {
        this.CDN = value;
    }
    
    public boolean isVersionRnCombined() {
        return IsVersionRnCombined;
    }

    public void setVersionRNCombined(boolean isVersionRnCombined) {
        this.IsVersionRnCombined = isVersionRnCombined;
    }

    public boolean isSSLModifiable() {
        return IsSSLModifiable;
    }

    public void setSSLModifiable(boolean isSSLModifiable) {
        this.IsSSLModifiable = isSSLModifiable;
    }

    public String getAppendToTitle() {
        return this.AppendToTitle;
    }
    
    public void setAppendToTitle(String value) {
        this.AppendToTitle = value;
    }
    
    /**
     * Exists as a container of CDTSEnvironment matching the format of the
     * JSON file the data is read from.
     */
    public static class CDTSEnvironmentList {
        //NOTE: Name of instance variable will have a capital first letter
        //      to match the format of the JSON file.
        private ArrayList<CDTSEnvironment>  Environments;
        
        public CDTSEnvironmentList() {
        }
        
        public ArrayList<CDTSEnvironment> getEnvironments() {
            return this.Environments;
        }
        
        public void setEnvironments(ArrayList<CDTSEnvironment> value) {
            this.Environments = value;
        }
    }
}
