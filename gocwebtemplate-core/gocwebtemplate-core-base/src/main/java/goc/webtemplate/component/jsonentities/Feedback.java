package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

public class Feedback implements Serializable {
    private boolean enabled;
    private String  href;
    private String text;
    private String theme;
    private String section;
    private String legacyBtnUrl;    
    
    public Feedback () {}
    
    public Feedback(boolean enabled, String href, String text, String theme, String section, String legacyBtnUrl) {
        this.enabled = enabled;
        this.href = href;
        this.text = text;
        this.theme = theme;
        this.section = section;
        this.legacyBtnUrl = legacyBtnUrl;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public String getHref() {
        return href;
    }
    
    public void setHref(String href) {
        this.href = href;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getTheme() {
        return theme;
    }
    
    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    public String getSection() {
        return section;
    }
    
    public void setSection(String section) {
        this.section = section;
    }
    
    public String getLegacyBtnUrl() {
        return legacyBtnUrl;
    }
    
    public void setLegacyBtnUrl(String legacyBtnUrl) {
        this.legacyBtnUrl = legacyBtnUrl;
    }
}
