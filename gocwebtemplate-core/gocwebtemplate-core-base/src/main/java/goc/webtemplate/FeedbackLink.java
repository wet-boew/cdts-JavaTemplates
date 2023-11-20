package goc.webtemplate;

import java.io.Serializable;

public class FeedbackLink implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean enabled;
    private String  url;
    private String  urlFr;
    private String  text;
    private String  textFr;
    private String  theme;
    private String  section;
    
    public FeedbackLink() {
    }
    
    public FeedbackLink(boolean enabled, String url, String urlFr, String text, String textFr, String theme, String section) {
        this.enabled = enabled;
        this.url = url;
        this.urlFr = urlFr;
        this.text = text;
        this.textFr = textFr;
        this.theme = theme;
        this.section = section;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlFr() {
        return urlFr;
    }

    public void setUrlFr(String urlFr) {
        this.urlFr = urlFr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextFr() {
        return textFr;
    }

    public void setTextFr(String textFr) {
        this.textFr = textFr;
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
