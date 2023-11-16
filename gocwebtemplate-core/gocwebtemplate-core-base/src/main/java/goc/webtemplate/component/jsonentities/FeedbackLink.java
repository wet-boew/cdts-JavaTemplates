package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

public class FeedbackLink implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean shown;
    private String  url;
    private String  text;
    private String  theme;
    private String  section;
    
    public FeedbackLink() {
    }
    
    public FeedbackLink(boolean shown, String url) {
        this.shown = shown;
        this.url = url;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
