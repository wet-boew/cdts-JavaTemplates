package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

public class FeedbackLink implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean shown;
    private String  url;
    
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
}
